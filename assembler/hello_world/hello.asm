# syscall "write"
.macro write fd, buf, len           # define a macro with three parameters
    movl $4, %eax                   # set the syscall to be executed (4 is write)
    movl \fd, %ebx                  # set the 1. parameter for write (file descriptor)
    movl \buf, %ecx                 # set the 2. parameter for write (buffer)
    movl \len, %edx                 # set the 3. parameter for write (length of string)
    int $0x80                       # execute the syscall
.endm

.data                               # begin the data subsection (the literals, etc.)

hello:
    .string "Hello, world!\n"       # create a string literal (null-terminated)
    hellolen = . - hello            # set hellolen to the length of the string

spam:
    .string "I don't like spam.\n"  # create a string literal (null-terminated)
    spamlen = . - spam              # set spamlen to the length of the string

.text                               # begin the text subsection (the actual program)

.global _start                      # create a globally visible symbol for the linker

_start:                             # _start is the entry point into the program
    write $1, $hello, $hellolen     # write the specified string to stdio
    write $1, $spam, $spamlen       # write the specified string to stdio

    movl $1, %eax                   # set the syscall to be executed (1 is exit)
    movl $0, %ebx                   # set the parameter for exit (return value)
    int $0x80
