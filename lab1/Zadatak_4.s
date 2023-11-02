	.file	"Zadatak_4.cpp"
	.intel_syntax noprefix
	.section .rdata,"dr"
__ZStL19piecewise_construct:
	.space 1
.lcomm __ZStL8__ioinit,1,1
	.section	.text$_ZN9CoolClass3setEi,"x"
	.linkonce discard
	.align 2
	.globl	__ZN9CoolClass3setEi
	.def	__ZN9CoolClass3setEi;	.scl	2;	.type	32;	.endef
__ZN9CoolClass3setEi:
LFB1445:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 4
	mov	DWORD PTR [ebp-4], ecx
	mov	eax, DWORD PTR [ebp-4]
	mov	edx, DWORD PTR [ebp+8]
	mov	DWORD PTR [eax+4], edx
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret	4
	.cfi_endproc
LFE1445:
	.section	.text$_ZN9CoolClass3getEv,"x"
	.linkonce discard
	.align 2
	.globl	__ZN9CoolClass3getEv
	.def	__ZN9CoolClass3getEv;	.scl	2;	.type	32;	.endef
__ZN9CoolClass3getEv:
LFB1446:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 4
	mov	DWORD PTR [ebp-4], ecx
	mov	eax, DWORD PTR [ebp-4]
	mov	eax, DWORD PTR [eax+4]
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1446:
	.section	.text$_ZN13PlainOldClass3setEi,"x"
	.linkonce discard
	.align 2
	.globl	__ZN13PlainOldClass3setEi
	.def	__ZN13PlainOldClass3setEi;	.scl	2;	.type	32;	.endef
__ZN13PlainOldClass3setEi:
LFB1447:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 4
	mov	DWORD PTR [ebp-4], ecx
	mov	eax, DWORD PTR [ebp-4]
	mov	edx, DWORD PTR [ebp+8]
	mov	DWORD PTR [eax], edx
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret	4
	.cfi_endproc
LFE1447:
	.section	.text$_ZN4BaseC2Ev,"x"
	.linkonce discard
	.align 2
	.globl	__ZN4BaseC2Ev
	.def	__ZN4BaseC2Ev;	.scl	2;	.type	32;	.endef
__ZN4BaseC2Ev:
LFB1452:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 4
	mov	DWORD PTR [ebp-4], ecx
	mov	edx, OFFSET FLAT:__ZTV4Base+8
	mov	eax, DWORD PTR [ebp-4]
	mov	DWORD PTR [eax], edx
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1452:
	.section	.text$_ZN9CoolClassC1Ev,"x"
	.linkonce discard
	.align 2
	.globl	__ZN9CoolClassC1Ev
	.def	__ZN9CoolClassC1Ev;	.scl	2;	.type	32;	.endef
__ZN9CoolClassC1Ev:
LFB1455:
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
	call	__ZN4BaseC2Ev					#ovdje se poziva konstruktor za baznu klasu (Base)
	mov	edx, OFFSET FLAT:__ZTV9CoolClass+8  ##tu se stvara virtualna tablica za klasu CoolClass (+8 znaci da sprema dva puta po 4 bajta za dvije funkcije)
	mov	eax, DWORD PTR [ebp-12]
	mov	DWORD PTR [eax], edx
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1455:
	.def	___main;	.scl	2;	.type	32;	.endef
	.text
	.globl	_main
	.def	_main;	.scl	2;	.type	32;	.endef
_main:
LFB1449:
	.cfi_startproc
	lea	ecx, [esp+4]
	.cfi_def_cfa 1, 0
	and	esp, -16
	push	DWORD PTR [ecx-4]
	push	ebp
	.cfi_escape 0x10,0x5,0x2,0x75,0
	mov	ebp, esp
	push	ebx
	push	ecx
	.cfi_escape 0xf,0x3,0x75,0x78,0x6
	.cfi_escape 0x10,0x3,0x2,0x75,0x7c
	sub	esp, 32
	call	___main
	mov	DWORD PTR [esp], 8			#ovdje sprema 8 bajtova za klasu CoolClass
	call	__Znwj					#ovdje poziva konstruktor od CoolClass
	mov	ebx, eax
	mov	ecx, ebx
	call	__ZN9CoolClassC1Ev
	mov	DWORD PTR [ebp-12], ebx
	lea	eax, [ebp-16]
	mov	DWORD PTR [esp], 42						#tu se 42 se stavlja na stog za set od poc
	mov	ecx, eax
	call	__ZN13PlainOldClass3setEi			#tu se poziva set funkcija od PlainOldClass
	sub	esp, 4									#tu se poziv te funkcije mice sa stoga
	mov	eax, DWORD PTR [ebp-12]
	mov	eax, DWORD PTR [eax]					#ovdje se sprema pointer na virtualnu tablicu
	mov	edx, DWORD PTR [eax]					#ovdje se sprema adresa prve funkcije virtualne tablice (odnosno set funkcija CoolClass)
	mov	eax, DWORD PTR [ebp-12]
	mov	DWORD PTR [esp], 42						#tu se 42 stavlja na stog za set od pb
	mov	ecx, eax
	call	edx									#ovdje se poziva funkcija set od CoolClass
	sub	esp, 4
	mov	eax, 0
	lea	esp, [ebp-8]
	pop	ecx
	.cfi_restore 1
	.cfi_def_cfa 1, 0
	pop	ebx
	.cfi_restore 3
	pop	ebp
	.cfi_restore 5
	lea	esp, [ecx-4]
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1449:
	.globl	__ZTV9CoolClass
	.section	.rdata$_ZTV9CoolClass,"dr"
	.linkonce same_size
	.align 4
__ZTV9CoolClass:								#tu je definicija virtualne tablice za CoolClass
	.long	0
	.long	__ZTI9CoolClass
	.long	__ZN9CoolClass3setEi
	.long	__ZN9CoolClass3getEv
	.globl	__ZTV4Base
	.section	.rdata$_ZTV4Base,"dr"
	.linkonce same_size
	.align 4
__ZTV4Base:
	.long	0
	.long	__ZTI4Base
	.long	___cxa_pure_virtual
	.long	___cxa_pure_virtual
	.globl	__ZTI9CoolClass
	.section	.rdata$_ZTI9CoolClass,"dr"
	.linkonce same_size
	.align 4
__ZTI9CoolClass:
	.long	__ZTVN10__cxxabiv120__si_class_type_infoE+8
	.long	__ZTS9CoolClass
	.long	__ZTI4Base
	.globl	__ZTS9CoolClass
	.section	.rdata$_ZTS9CoolClass,"dr"
	.linkonce same_size
	.align 4
__ZTS9CoolClass:
	.ascii "9CoolClass\0"
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
LFB1880:
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
LFE1880:
	.def	__Z41__static_initialization_and_destruction_0ii;	.scl	3;	.type	32;	.endef
__Z41__static_initialization_and_destruction_0ii:
LFB1879:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 24
	cmp	DWORD PTR [ebp+8], 1
	jne	L12
	cmp	DWORD PTR [ebp+12], 65535
	jne	L12
	mov	ecx, OFFSET FLAT:__ZStL8__ioinit
	call	__ZNSt8ios_base4InitC1Ev
	mov	DWORD PTR [esp], OFFSET FLAT:___tcf_0
	call	_atexit
L12:
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1879:
	.def	__GLOBAL__sub_I_main;	.scl	3;	.type	32;	.endef
__GLOBAL__sub_I_main:
LFB1881:
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
LFE1881:
	.section	.ctors,"w"
	.align 4
	.long	__GLOBAL__sub_I_main
	.ident	"GCC: (MinGW.org GCC-6.3.0-1) 6.3.0"
	.def	__Znwj;	.scl	2;	.type	32;	.endef
	.def	___cxa_pure_virtual;	.scl	2;	.type	32;	.endef
	.def	__ZNSt8ios_base4InitD1Ev;	.scl	2;	.type	32;	.endef
	.def	__ZNSt8ios_base4InitC1Ev;	.scl	2;	.type	32;	.endef
	.def	_atexit;	.scl	2;	.type	32;	.endef
