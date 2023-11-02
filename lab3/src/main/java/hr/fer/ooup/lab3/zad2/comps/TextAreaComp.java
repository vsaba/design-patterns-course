package hr.fer.ooup.lab3.zad2.comps;

import hr.fer.ooup.lab3.zad2.location.Location;
import hr.fer.ooup.lab3.zad2.location.LocationRange;
import hr.fer.ooup.lab3.zad2.model.TextEditorModel;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class TextAreaComp extends JComponent {


    private TextEditorModel model;

    private final int COLUMN_INSET = 15;
    private final int ROW_INSET = 10;

    public TextAreaComp(TextEditorModel model) {
        this.model = model;

        attachListeners();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        Graphics2D g2d = (Graphics2D) g;
        Font currentFont = g2d.getFont();

        g2d.setFont(currentFont.deriveFont(currentFont.getSize() * 1.4f));

        if (this.model.getSelectionRange().hasSelection()) {
            paintSelection(g2d);
        }

        paintLines(g2d);
        paintCursor(g2d);

        return;
    }

    private void attachListeners() {

        this.model.addCursorObserver((loc -> this.repaint()));
        this.model.addTextObserver(() -> this.repaint());

        return;
    }


    private void paintSelection(Graphics2D g2d) {

        Color originalColor = g2d.getColor();
        g2d.setColor(Color.CYAN);


        LocationRange range = this.model.getSelectionRange();
        Location start = range.getLocationStart();
        Location end = range.getLocationEnd();


        Iterator<String> selectedLines = this.model.linesRange(start.getRow(), end.getRow());

        FontMetrics metrics = g2d.getFontMetrics();

        int i = start.getRow();

        while (selectedLines.hasNext()) {

            String line = selectedLines.next();

            if(line.isEmpty()){
                line = " ";
            }

            int y = COLUMN_INSET + (i - 1) * metrics.getHeight();
            int height = metrics.getHeight();

            if (start.getRow() == end.getRow()) {
                //prvi i zadnji su isti, crtaj od start.getcol do end.getcol po liniji

                int x = ROW_INSET + metrics.stringWidth(line.substring(0, start.getCol()));

                int width = metrics.stringWidth(line.substring(start.getCol(), end.getCol()));

                g2d.fillRect(x, y, width, height);
                break;
            }

            if (i == start.getRow()) {
                //na prvom smo a prvi i zadnji nisu isti, crtaj od start do kraja linija
                int x = ROW_INSET + metrics.stringWidth(line.substring(0, start.getCol()));
                int width = metrics.stringWidth(line.substring(start.getCol()));
                g2d.fillRect(x, y, width, height);
            } else if (i == end.getRow()) {
                int x = ROW_INSET;
                int width = metrics.stringWidth(line.substring(0, end.getCol()));
                g2d.fillRect(x, y, width, height);
                //na zadnjem smo, a prvi i zadnji nisu isti, crtaj od pocetka linije do end
            } else {
                int x = ROW_INSET;
                int width = metrics.stringWidth(line);
                g2d.fillRect(x, y, width, height);
                //u retku smo izmedu, crtaj po cijeloj duzini linije
            }

            i++;
        }


        g2d.setColor(originalColor);

        return;
    }


    private void paintLines(Graphics g2d) {

        Iterator<String> linesIterator = this.model.allLines();

        FontMetrics metrics = g2d.getFontMetrics();

        int i = 0;

        while (linesIterator.hasNext()) {
            String line = linesIterator.next();

            if (i == 0) {
                g2d.drawString(line, ROW_INSET, COLUMN_INSET);
                i++;
                continue;
            }

            g2d.drawString(line, ROW_INSET, COLUMN_INSET + metrics.getHeight() * i);
            i++;

        }

        return;
    }

    private void paintCursor(Graphics g2d) {
        Location loc = this.model.getCursorLocation();

        String line = this.model.getLines().get(loc.getRow());

        FontMetrics metrics = g2d.getFontMetrics();

        String substring = line.substring(0, loc.getCol());

        int x = metrics.stringWidth(substring) + ROW_INSET;
        int y = (loc.getRow()) * metrics.getHeight() + COLUMN_INSET;
        int end = y - metrics.getHeight();

        g2d.drawLine(x, y, x, y - metrics.getHeight() * 3 / 4);

        return;
    }


}
