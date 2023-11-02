package hr.fer.ooup.lab3.zad2;

import hr.fer.ooup.lab3.zad2.actions.cursor.*;
import hr.fer.ooup.lab3.zad2.actions.edit.*;
import hr.fer.ooup.lab3.zad2.actions.file.CloseWindowAction;
import hr.fer.ooup.lab3.zad2.actions.file.OpenFileAction;
import hr.fer.ooup.lab3.zad2.actions.file.SaveFileAction;
import hr.fer.ooup.lab3.zad2.clipboard.ClipboardObserver;
import hr.fer.ooup.lab3.zad2.clipboard.ClipboardStack;
import hr.fer.ooup.lab3.zad2.comps.TextAreaComp;
import hr.fer.ooup.lab3.zad2.location.Location;
import hr.fer.ooup.lab3.zad2.model.TextEditorModel;
import hr.fer.ooup.lab3.zad2.model.impl.DefaultTextEditorModel;
import hr.fer.ooup.lab3.zad2.plugins.Plugin;
import hr.fer.ooup.lab3.zad2.undo.UndoManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TextEditor extends JFrame {


    private TextAreaComp comp;
    private TextEditorModel model;
    private ClipboardStack clipboardStack;
    private JPanel statusBar;
    private JMenuBar menuBar;

    public TextEditor(String name) {
        super(name);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        this.model = new DefaultTextEditorModel("Hello from my notepad\npozdrav\nciao ciao");
        this.comp = new TextAreaComp(model);
        this.clipboardStack = new ClipboardStack();

        initGUI();

    }

    private void initGUI() {

        this.getContentPane().setLayout(new BorderLayout());


        initActions();
        initMenu();
        initToolbar();
        initListeners();
        initStatusBar();
        initPlugins();

        this.getContentPane().add(comp, BorderLayout.CENTER);

    }


    private void initMenu() {

        ActionMap actionMap = this.comp.getActionMap();

        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem(new OpenFileAction(model)));
        fileMenu.add(new JMenuItem(new SaveFileAction(model)));
        fileMenu.add(new JMenuItem(new CloseWindowAction(this)));


        JMenu editMenu = new JMenu("Edit");
        editMenu.add(new JMenuItem(actionMap.get("Undo")));
        editMenu.add(new JMenuItem(actionMap.get("Redo")));
        editMenu.add(new JMenuItem(actionMap.get("Cut")));
        editMenu.add(new JMenuItem(actionMap.get("Copy")));
        editMenu.add(new JMenuItem(actionMap.get("Paste")));
        editMenu.add(new JMenuItem(actionMap.get("Paste and Take")));
        editMenu.add(new JMenuItem(actionMap.get("Delete")));
        editMenu.add(new JMenuItem(actionMap.get("Clear document")));

        JMenu moveMenu = new JMenu("Move");
        moveMenu.add(new JMenuItem(new MoveCursorToStartAction(this.model)));
        moveMenu.add(new JMenuItem(new MoveCursorToEndAction(this.model)));

        JMenu pluginMenu = new JMenu("Plugins");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(moveMenu);
        menuBar.add(pluginMenu);


        this.setJMenuBar(menuBar);
    }

    private void initStatusBar() {

        statusBar = new JPanel(new GridLayout(1, 0));

        JLabel cursorPosition = new JLabel("Row: " + (model.getCursorLocation().getRow() + 1) + "  Col: " + model.getCursorLocation().getCol());
        JLabel documentSize = new JLabel("Size: " + model.getLines().size());
        statusBar.add(cursorPosition);
        statusBar.add(documentSize);

        cursorPosition.setHorizontalAlignment(SwingConstants.LEFT);
        documentSize.setHorizontalAlignment(SwingConstants.RIGHT);

        this.getContentPane().add(statusBar, BorderLayout.PAGE_END);
    }

    private void initToolbar() {

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(true);

        ActionMap actionMap = this.comp.getActionMap();

        toolBar.add(new JButton(actionMap.get("Undo")));
        toolBar.add(new JButton(actionMap.get("Redo")));

        toolBar.addSeparator();

        toolBar.add(new JButton(actionMap.get("Cut")));
        toolBar.add(new JButton(actionMap.get("Copy")));
        toolBar.add(new JButton(actionMap.get("Paste")));

        for (Component component : toolBar.getComponents()) {
            component.setFocusable(false);
        }

        this.getContentPane().add(toolBar, BorderLayout.PAGE_START);
    }

    private void initActions() {
        List<Action> actions = new ArrayList<>();

        actions.add(new CursorLeftAction(this.model));
        actions.add(new CursorRightAction(this.model));
        actions.add(new CursorDownAction(this.model));
        actions.add(new CursorUpAction(this.model));

        actions.add(new MoveCursorToEndAction(this.model));
        actions.add(new MoveCursorToStartAction(this.model));

        actions.add(new SelectionDownAction(this.model));
        actions.add(new SelectionUpAction(this.model));
        actions.add(new SelectionLeftAction(this.model));
        actions.add(new SelectionRightAction(this.model));

        actions.add(new CopyAction(this.model, this.clipboardStack));
        actions.add(new CutAction(this.model, this.clipboardStack));
        actions.add(new PasteAction(this.model, this.clipboardStack));
        actions.add(new PasteAndRemoveAction(this.model, this.clipboardStack));

        actions.add(new DeleteBeforeAction(this.model));
        actions.add(new DeleteAfterAction(this.model));
        actions.add(new ClearDocumentAction(this.model));

        actions.add(new UndoAction());
        actions.add(new RedoAction());

        addActions(actions);

    }

    private void initListeners() {
        this.comp.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();

                if (KeyEvent.CHAR_UNDEFINED == c || e.isActionKey() || e.isControlDown() || e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
                    return;
                }

                model.insert(c);

            }
        });

        this.clipboardStack.addClipboardListener(() -> {
            boolean enabled = !clipboardStack.isEmpty();
            ActionMap actionMap = comp.getActionMap();

            actionMap.get("Paste").setEnabled(enabled);
            actionMap.get("Paste and Take").setEnabled(enabled);
        });


        UndoManager.getInstance().addUndoListeners(() -> {
            ActionMap actionMap = comp.getActionMap();

            actionMap.get("Undo").setEnabled(!UndoManager.getInstance().undoStackIsEmpty());
            actionMap.get("Redo").setEnabled(!UndoManager.getInstance().redoStackIsEmpty());
        });

        this.model.addTextObserver(() -> {
            updateStatusBar();
        });

        this.model.addCursorObserver(l -> {

            updateStatusBar();
        });
    }

    private void initPlugins() {
        List<Plugin> plugins = loadPlugins();


        JMenu pluginMenu = menuBar.getMenu(3);

        for (Plugin plugin : plugins) {
            pluginMenu.add(new JMenuItem(new AbstractAction(plugin.getName()) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    plugin.execute(model, UndoManager.getInstance(), clipboardStack);
                }
            }));
        }


    }

    private List<Plugin> loadPlugins() {

        List<Plugin> plugins = new ArrayList<>();


        File pluginDirectory = new File("src/main/java/hr/fer/ooup/lab3/zad2/plugins/impl");


        if (!pluginDirectory.isDirectory()) {
            throw new IllegalArgumentException("The provided path is not a directory");
        }

        for (File file : Objects.requireNonNull(pluginDirectory.listFiles())) {
            Plugin plugin = loadPlugin(file.getName().split("\\.")[0]);

            if (plugin != null) {
                plugins.add(plugin);
            }
        }

        return plugins;
    }

    private Plugin loadPlugin(String pluginName) {

        Plugin plugin = null;
        Class<Plugin> pluginClass = null;
        Constructor<?> ctr = null;
        try {
            pluginClass = (Class<Plugin>) Class.forName("hr.fer.ooup.lab3.zad2.plugins.impl." + pluginName);
            ctr = pluginClass.getConstructor();
            plugin = (Plugin) ctr.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return plugin;
    }


    private void addActions(List<Action> actions) {

        for (Action action : actions) {
            comp.getInputMap().put((KeyStroke) action.getValue(Action.ACCELERATOR_KEY), action.getValue(Action.NAME));
            comp.getActionMap().put(action.getValue(Action.NAME), action);
        }

        return;
    }

    private void updateStatusBar() {

        JLabel cursor = (JLabel) statusBar.getComponent(0);
        JLabel documentSize = (JLabel) statusBar.getComponent(1);

        Location loc = model.getCursorLocation();
        cursor.setText("Row: " + (loc.getRow() + 1) + "  Col: " + loc.getCol());
        documentSize.setText("Size: " + model.getLines().size());

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TextEditor("Notepad").setVisible(true));
    }

}
