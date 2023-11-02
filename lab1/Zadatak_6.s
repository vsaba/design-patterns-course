	.file	"Zadatak_6.cpp"
	.intel_syntax noprefix
	.section .rdata,"dr"
__ZStL19piecewise_construct:
	.space 1
.lcomm __ZStL8__ioinit,1,1
	.section	.text$_ZN4BaseC2Ev,"x"
	.linkonce discard
	.align 2
	.globl	__ZN4BaseC2Ev
	.def	__ZN4BaseC2Ev;	.scl	2;	.type	32;	.endef
__ZN4BaseC2Ev:
LFB1446:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 24
	mov	DWORD PTR [ebp-12], ecx
	mov	edx, OFFSET FLAT:__ZTV4Base+8
	mov	eax, DWORD PTR [ebp-12]
	mov	DWORD PTR [eax], edx
	mov	eax, DWORD PTR [ebp-12]
	mov	ecx, eax
	call	__ZN4Base6metodaEv			#tu se poziva metoda() klase Base
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1446:
	.section .rdata,"dr"
LC0:
	.ascii "ja sam bazna implementacija!\0"
	.section	.text$_ZN4Base15virtualnaMetodaEv,"x"
	.linkonce discard
	.align 2
	.globl	__ZN4Base15virtualnaMetodaEv
	.def	__ZN4Base15virtualnaMetodaEv;	.scl	2;	.type	32;	.endef
__ZN4Base15virtualnaMetodaEv:
LFB1448:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 40
	mov	DWORD PTR [ebp-12], ecx
	mov	DWORD PTR [esp], OFFSET FLAT:LC0
	call	_puts
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1448:
	.section .rdata,"dr"
LC1:
	.ascii "Metoda kaze: \0"
	.section	.text$_ZN4Base6metodaEv,"x"
	.linkonce discard
	.align 2
	.globl	__ZN4Base6metodaEv
	.def	__ZN4Base6metodaEv;	.scl	2;	.type	32;	.endef
__ZN4Base6metodaEv:
LFB1449:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 40
	mov	DWORD PTR [ebp-12], ecx
	mov	DWORD PTR [esp], OFFSET FLAT:LC1
	call	_printf
	mov	eax, DWORD PTR [ebp-12]
	mov	eax, DWORD PTR [eax]
	mov	eax, DWORD PTR [eax]
	mov	edx, DWORD PTR [ebp-12]
	mov	ecx, edx
	call	eax
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1449:
	.section	.text$_ZN7DerivedC1Ev,"x"
	.linkonce discard
	.align 2
	.globl	__ZN7DerivedC1Ev
	.def	__ZN7DerivedC1Ev;	.scl	2;	.type	32;	.endef
__ZN7DerivedC1Ev:
LFB1452:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 24
	mov	DWORD PTR [ebp-12], ecx
	mov	eax, DWORD PTR [ebp-12]
	mov	ecx, eax
	call	__ZN4BaseC2Ev
	mov	edx, OFFSET FLAT:__ZTV7Derived+8
	mov	eax, DWORD PTR [ebp-12]
	mov	DWORD PTR [eax], edx
	mov	eax, DWORD PTR [ebp-12]
	mov	ecx, eax
	call	__ZN4Base6metodaEv
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1452:
	.section .rdata,"dr"
	.align 4
LC2:
	.ascii "ja sam izvedena implementacija!\0"
	.section	.text$_ZN7Derived15virtualnaMetodaEv,"x"
	.linkonce discard
	.align 2
	.globl	__ZN7Derived15virtualnaMetodaEv
	.def	__ZN7Derived15virtualnaMetodaEv;	.scl	2;	.type	32;	.endef
__ZN7Derived15virtualnaMetodaEv:
LFB1453:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 40
	mov	DWORD PTR [ebp-12], ecx
	mov	DWORD PTR [esp], OFFSET FLAT:LC2
	call	_puts
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1453:
	.def	___main;	.scl	2;	.type	32;	.endef
	.text
	.globl	_main
	.def	_main;	.scl	2;	.type	32;	.endef
_main:
LFB1454:
	.cfi_startproc
	.cfi_personality 0,___gxx_personality_v0
	.cfi_lsda 0,LLSDA1454
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	push	esi
	push	ebx
	and	esp, -16
	sub	esp, 32
	.cfi_offset 6, -12
	.cfi_offset 3, -16
	call	___main
	mov	DWORD PTR [esp], 4
LEHB0:
	call	__Znwj
LEHE0:
	mov	ebx, eax
	mov	ecx, ebx
LEHB1:
	call	__ZN7DerivedC1Ev
LEHE1:
	mov	DWORD PTR [esp+28], ebx
	mov	eax, DWORD PTR [esp+28]
	mov	ecx, eax
LEHB2:
	call	__ZN4Base6metodaEv
	mov	eax, 0
	jmp	L10
L9:
	mov	esi, eax
	mov	DWORD PTR [esp+4], 4
	mov	DWORD PTR [esp], ebx
	call	__ZdlPvj
	mov	eax, esi
	mov	DWORD PTR [esp], eax
	call	__Unwind_Resume
LEHE2:
L10:
	lea	esp, [ebp-8]
	pop	ebx
	.cfi_restore 3
	pop	esi
	.cfi_restore 6
	pop	ebp
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1454:
	.def	___gxx_personality_v0;	.scl	2;	.type	32;	.endef
	.section	.gcc_except_table,"w"
LLSDA1454:
	.byte	0xff
	.byte	0xff
	.byte	0x1
	.uleb128 LLSDACSE1454-LLSDACSB1454
LLSDACSB1454:
	.uleb128 LEHB0-LFB1454
	.uleb128 LEHE0-LEHB0
	.uleb128 0
	.uleb128 0
	.uleb128 LEHB1-LFB1454
	.uleb128 LEHE1-LEHB1
	.uleb128 L9-LFB1454
	.uleb128 0
	.uleb128 LEHB2-LFB1454
	.uleb128 LEHE2-LEHB2
	.uleb128 0
	.uleb128 0
LLSDACSE1454:
	.text
	.globl	__ZTV7Derived
	.section	.rdata$_ZTV7Derived,"dr"
	.linkonce same_size
	.align 4
__ZTV7Derived:
	.long	0
	.long	__ZTI7Derived
	.long	__ZN7Derived15virtualnaMetodaEv
	.globl	__ZTV4Base
	.section	.rdata$_ZTV4Base,"dr"
	.linkonce same_size
	.align 4
__ZTV4Base:
	.long	0
	.long	__ZTI4Base
	.long	__ZN4Base15virtualnaMetodaEv
	.globl	__ZTI7Derived
	.section	.rdata$_ZTI7Derived,"dr"
	.linkonce same_size
	.align 4
__ZTI7Derived:
	.long	__ZTVN10__cxxabiv120__si_class_type_infoE+8
	.long	__ZTS7Derived
	.long	__ZTI4Base
	.globl	__ZTS7Derived
	.section	.rdata$_ZTS7Derived,"dr"
	.linkonce same_size
	.align 4
__ZTS7Derived:
	.ascii "7Derived\0"
	.globl	__ZTI4Base
	.section	.rdata$_ZTI4Base,"dr"
	.linkonce same_size
	.align 4
__ZTI4Base:
	.long	__ZTVN10__cxxabiv117__class_type_infoE+8
	.long	__ZTS4Base
	.globl	__ZTS4Base
	.section	.rdata$_ZTS4Base,"dr"
	.linkonce same_size
	.align 4
__ZTS4Base:
	.ascii "4Base\0"
	.text
	.def	___tcf_0;	.scl	3;	.type	32;	.endef
___tcf_0:
LFB1879:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 8
	mov	ecx, OFFSET FLAT:__ZStL8__ioinit
	call	__ZNSt8ios_base4InitD1Ev
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1879:
	.def	__Z41__static_initialization_and_destruction_0ii;	.scl	3;	.type	32;	.endef
__Z41__static_initialization_and_destruction_0ii:
LFB1878:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 24
	cmp	DWORD PTR [ebp+8], 1
	jne	L14
	cmp	DWORD PTR [ebp+12], 65535
	jne	L14
	mov	ecx, OFFSET FLAT:__ZStL8__ioinit
	call	__ZNSt8ios_base4InitC1Ev
	mov	DWORD PTR [esp], OFFSET FLAT:___tcf_0
	call	_atexit
L14:
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1878:
	.def	__GLOBAL__sub_I_main;	.scl	3;	.type	32;	.endef
__GLOBAL__sub_I_main:
LFB1880:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 24
	mov	DWORD PTR [esp+4], 65535
	mov	DWORD PTR [esp], 1
	call	__Z41__static_initialization_and_destruction_0ii
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1880:
	.section	.ctors,"w"
	.align 4
	.long	__GLOBAL__sub_I_main
	.ident	"GCC: (MinGW.org GCC-6.3.0-1) 6.3.0"
	.def	_puts;	.scl	2;	.type	32;	.endef
	.def	_printf;	.scl	2;	.type	32;	.endef
	.def	__Znwj;	.scl	2;	.type	32;	.endef
	.def	__ZdlPvj;	.scl	2;	.type	32;	.endef
	.def	__Unwind_Resume;	.scl	2;	.type	32;	.endef
	.def	__ZNSt8ios_base4InitD1Ev;	.scl	2;	.type	32;	.endef
	.def	__ZNSt8ios_base4InitC1Ev;	.scl	2;	.type	32;	.endef
	.def	_atexit;	.scl	2;	.type	32;	.endef
