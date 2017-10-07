public class Data {
    
    public static final long MAP_PAWN   = 0x00FF00000000FF00l;
    public static final long MAP_KNIGHT = 0x4200000000000042l;
    public static final long MAP_BISHOP = 0x2400000000000024l;
    public static final long MAP_ROOK   = 0x8100000000000081l;
    public static final long MAP_QUEEN  = 0x1000000000000010l;
    public static final long MAP_KING   = 0x0800000000000008l;
    public static final long MAP_BLACK  = 0x000000000000FFFFl;
    public static final long MAP_WHITE  = 0xFFFF000000000000l;
    
    public static final int FILE_A = 0;
    public static final int FILE_B = 1;
    public static final int FILE_C = 2;
    public static final int FILE_D = 3;
    public static final int FILE_E = 4;
    public static final int FILE_F = 5;
    public static final int FILE_G = 6;
    public static final int FILE_H = 7;
    
    public static final int RANK_1 = 0;
    public static final int RANK_2 = 1;
    public static final int RANK_3 = 2;
    public static final int RANK_4 = 3;
    public static final int RANK_5 = 4;
    public static final int RANK_6 = 5;
    public static final int RANK_7 = 6;
    public static final int RANK_8 = 7;
    
    public static long[][] MAPS_ATTACK_KING = {
        { // TODO Genau anders herum machen
            0x40C0000000000000l, // FILE_A RANK_1
            0xA0E0000000000000l, // FILE_B RANK_1
            0x5070000000000000l, // FILE_C RANK_1
            0x2838000000000000l, // FILE_D RANK_1
            0x141C000000000000l, // FILE_E RANK_1
            0x0A0E000000000000l, // FILE_F RANK_1
            0x0507000000000000l, // FILE_G RANK_1
            0x0203000000000000l  // FILE_H RANK_1
        },
        { 
            0xC040C00000000000l,
            0xE0A0E00000000000l,
            0x7050700000000000l,
            0x3828380000000000l,
            0x1C141C0000000000l,
            0x0E0A0E0000000000l,
            0x0705070000000000l,
            0x0302030000000000l,
        },
        { 
            0x00C040C000000000l,
            0x00E0A0E000000000l,
            0x0070507000000000l,
            0x0038283800000000l,
            0x001C141C00000000l,
            0x000E0A0E00000000l,
            0x0007050700000000l,
            0x0003020300000000l,
        },
        { 
            0x0000C040C0000000l,
            0x0000E0A0E0000000l,
            0x0000705070000000l,
            0x0000382838000000l,
            0x00001C141C000000l,
            0x00000E0A0E000000l,
            0x0000070507000000l,
            0x0000030203000000l,
        },
        { 
            0x000000C040C00000l,
            0x000000E0A0E00000l,
            0x0000007050700000l,
            0x0000003828380000l,
            0x0000001C141C0000l,
            0x0000000E0A0E0000l,
            0x0000000705070000l,
            0x0000000302030000l,
        },
        {
            0x00000000C040C000l,
            0x00000000E0A0E000l,
            0x0000000070507000l,
            0x0000000038283800l,
            0x000000001C141C00l,
            0x000000000E0A0E00l,
            0x0000000007050700l,
            0x0000000003020300l,
        },
        { 
            0x0000000000C040C0l,
            0x0000000000E0A0E0l,
            0x0000000000705070l,
            0x0000000000382838l,
            0x00000000001C141Cl,
            0x00000000000E0A0El,
            0x0000000000070507l,
            0x0000000000030203l,
        },
        {
            0x000000000000C040l,
            0x000000000000E0A0l,
            0x0000000000007050l,
            0x0000000000003828l,
            0x0000000000001C14l,
            0x0000000000000E0Al,
            0x0000000000000705l,
            0x0000000000000302l,
        }
    };
    
    
    public static final long[] MAPS_RANK = {
        0xFF00000000000000l, // RANK_1
        0x00FF000000000000l, // RANK_2
        0x0000FF0000000000l, // RANK_3
        0x000000FF00000000l, // RANK_4
        0x00000000FF000000l, // RANK_5
        0x0000000000FF0000l, // RANK_6
        0x000000000000FF00l, // RANK_7
        0x00000000000000FFl  // RANK_8
    };
    
    public static final long[] MAPS_FILE = {
        0x8080808080808080l, // FILE_A
        0x4040404040404040l, // FILE_B
        0x2020202020202020l, // FILE_C
        0x1010101010101010l, // FILE_D
        0x0808080808080808l, // FILE_E
        0x0404040404040404l, // FILE_F
        0x0202020202020202l, // FILE_G
        0x0101010101010101l  // FILE_H
    };
    
    public static void main(final String[] args) {
        System.out.println(Long.toHexString(MAPS_ATTACK_KING[FILE_B][RANK_1]));
    }

    private static long[][] compute() {
        final long[][] moves = new long[8][8];
        for (int i = 0; i < 8; i++) { // i ist file, j ist rank
            for (int j = 0; j < 8; j++) {
                long move = 0;
                for (int k = 0; k < 8; k++) { // k ist file, l ist rank
                    for (int l = 0; l < 8; l++) {
                        final int file = i - k;
                        final int rank = j - l;
                        move <<= 1;
                        if (file == 1  && rank == 0  ||
                            file == -1 && rank == 0  ||
                            file == 0  && rank == 1  ||
                            file == 0  && rank == -1 ||
                            file == 1  && rank == 1  ||
                            file == -1 && rank == -1 ||
                            file == -1 && rank == 1  ||
                            file == 1  && rank == -1 ) {
                            move += 1;
                        }
                    }
                }
                System.out.println(Long.toHexString(move).toUpperCase() + ",");
                moves[i][j] = move;
            }
            System.out.println();
        }
        return moves;
    }
}
