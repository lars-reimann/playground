public class Data {

    public static final int A1 = 0;
    public static final int A2 = 1;
    public static final int A3 = 2;
    public static final int A4 = 3;
    public static final int A5 = 4;
    public static final int A6 = 5;
    public static final int A7 = 6;
    public static final int A8 = 7;

    public static final int B1 = 8;
    public static final int B2 = 9;
    public static final int B3 = 10;
    public static final int B4 = 11;
    public static final int B5 = 12;
    public static final int B6 = 13;
    public static final int B7 = 14;
    public static final int B8 = 15;

    public static final int C1 = 16;
    public static final int C2 = 17;
    public static final int C3 = 18;
    public static final int C4 = 19;
    public static final int C5 = 20;
    public static final int C6 = 21;
    public static final int C7 = 22;
    public static final int C8 = 23;

    public static final int D1 = 24;
    public static final int D2 = 25;
    public static final int D3 = 26;
    public static final int D4 = 27;
    public static final int D5 = 28;
    public static final int D6 = 29;
    public static final int D7 = 30;
    public static final int D8 = 31;

    public static final int E1 = 32;
    public static final int E2 = 33;
    public static final int E3 = 34;
    public static final int E4 = 35;
    public static final int E5 = 36;
    public static final int E6 = 37;
    public static final int E7 = 38;
    public static final int E8 = 39;

    public static final int F1 = 40;
    public static final int F2 = 41;
    public static final int F3 = 42;
    public static final int F4 = 43;
    public static final int F5 = 44;
    public static final int F6 = 45;
    public static final int F7 = 46;
    public static final int F8 = 47;

    public static final int G1 = 48;
    public static final int G2 = 49;
    public static final int G3 = 50;
    public static final int G4 = 51;
    public static final int G5 = 52;
    public static final int G6 = 53;
    public static final int G7 = 54;
    public static final int G8 = 55;

    public static final int H1 = 56;
    public static final int H2 = 57;
    public static final int H3 = 58;
    public static final int H4 = 59;
    public static final int H5 = 60;
    public static final int H6 = 61;
    public static final int H7 = 62;
    public static final int H8 = 63;

    public static final int FILE_A = 0;
    public static final int FILE_B = 1;
    public static final int FILE_C = 2;
    public static final int FILE_D = 3;
    public static final int FILE_E = 4;
    public static final int FILE_F = 5;
    public static final int FILE_G = 6;
    public static final int FILE_H = 7;

    public static final long MAP_BISHOP = 0x2400000000000024l;
    public static final long MAP_BLACK = 0x000000000000FFFFl;
    public static final long MAP_KING = 0x0800000000000008l;
    public static final long MAP_KNIGHT = 0x4200000000000042l;
    public static final long MAP_PAWN = 0x00FF00000000FF00l;
    public static final long MAP_QUEEN = 0x1000000000000010l;
    public static final long MAP_ROOK = 0x8100000000000081l;
    public static final long MAP_WHITE = 0xFFFF000000000000l;
    public static final long[] MAPS_ATTACK_KING = { 
        0x40C0000000000000l, // A1
            0xA0E0000000000000l, // B1
            0x5070000000000000l, // C1
            0x2838000000000000l, // D1
            0x141C000000000000l, // E1
            0x0A0E000000000000l, // F1
            0x0507000000000000l, // G1
            0x0203000000000000l, // H1
            0xC040C00000000000l, // A2
            0xE0A0E00000000000l, // B2
            0x7050700000000000l, // C2
            0x3828380000000000l, // D2
            0x1C141C0000000000l, // E2
            0x0E0A0E0000000000l, // F2
            0x0705070000000000l, // G2
            0x0302030000000000l, // H2
            0x00C040C000000000l, // A3
            0x00E0A0E000000000l, // B3
            0x0070507000000000l, // C3
            0x0038283800000000l, // D3
            0x001C141C00000000l, // E3
            0x000E0A0E00000000l, // F3
            0x0007050700000000l, // G3
            0x0003020300000000l, // H3
            0x0000C040C0000000l, // A4
            0x0000E0A0E0000000l, // B4
            0x0000705070000000l, // C4
            0x0000382838000000l, // D4
            0x00001C141C000000l, // E4
            0x00000E0A0E000000l, // F4
            0x0000070507000000l, // G4
            0x0000030203000000l, // H4
            0x000000C040C00000l, // A5
            0x000000E0A0E00000l, // B5
            0x0000007050700000l, // C5
            0x0000003828380000l, // D5
            0x0000001C141C0000l, // E5
            0x0000000E0A0E0000l, // F5
            0x0000000705070000l, // G5
            0x0000000302030000l, // H5
            0x00000000C040C000l, // A6
            0x00000000E0A0E000l, // B6
            0x0000000070507000l, // C6
            0x0000000038283800l, // D6
            0x000000001C141C00l, // E6
            0x000000000E0A0E00l, // F6
            0x0000000007050700l, // G6
            0x0000000003020300l, // H6
            0x0000000000C040C0l, // A7
            0x0000000000E0A0E0l, // B7
            0x0000000000705070l, // C7
            0x0000000000382838l, // D7
            0x00000000001C141Cl, // E7
            0x00000000000E0A0El, // F7
            0x0000000000070507l, // G7
            0x0000000000030203l, // H7
            0x000000000000C040l, // A8
            0x000000000000E0A0l, // B8
            0x0000000000007050l, // C8
            0x0000000000003828l, // D8
            0x0000000000001C14l, // E8
            0x0000000000000E0Al, // F8
            0x0000000000000705l, // G8
            0x0000000000000302l // H8
    };
    
    public static final long[] MAPS_ATTACK_KNIGHT = {
        0x0020400000000000l, // A1
        0x0010A00000000000l, // B1
        0x0088500000000000l, // C1
        0x0044280000000000l, // D1
        0x0022140000000000l, // E1
        0x00110A0000000000l, // F1
        0x0008050000000000l, // G1
        0x0004020000000000l, // H1
        0x2000204000000000l, // A2
        0x100010A000000000l, // B2
        0x8800885000000000l, // C2
        0x4400442800000000l, // D2
        0x2200221400000000l, // E2
        0x1100110A00000000l, // F2
        0x0800080500000000l, // G2
        0x0400040200000000l, // H2
        0x4020002040000000l, // A3
        0xA0100010A0000000l, // B3
        0x5088008850000000l, // C3
        0x2844004428000000l, // D3
        0x1422002214000000l, // E3
        0x0A1100110A000000l, // F3
        0x0508000805000000l, // G3
        0x0204000402000000l, // H3
        0x0040200020400000l, // A4
        0x00A0100010A00000l, // B4
        0x0050880088500000l, // C4
        0x0028440044280000l, // D4
        0x0014220022140000l, // E4
        0x000A1100110A0000l, // F4
        0x0005080008050000l, // G4
        0x0002040004020000l, // H4
        0x0000402000204000l, // A5
        0x0000A0100010A000l, // B5
        0x0000508800885000l, // C5
        0x0000284400442800l, // D5
        0x0000142200221400l, // E5
        0x00000A1100110A00l, // F5
        0x0000050800080500l, // G5
        0x0000020400040200l, // H5
        0x0000004020002040l, // A6
        0x000000A0100010A0l, // B6
        0x0000005088008850l, // C6
        0x0000002844004428l, // D6
        0x0000001422002214l, // E6
        0x0000000A1100110Al, // F6
        0x0000000508000805l, // G6
        0x0000000204000402l, // H6
        0x0000000040200020l, // A7
        0x00000000A0100010l, // B7
        0x0000000050880088l, // C7
        0x0000000028440044l, // D7
        0x0000000014220022l, // E7
        0x000000000A110011l, // F7
        0x0000000005080008l, // G7
        0x0000000002040004l, // H7
        0x0000000000402000l, // A8
        0x0000000000A01000l, // B8
        0x0000000000508800l, // C8
        0x0000000000284400l, // D8
        0x0000000000142200l, // E8
        0x00000000000A1100l, // F8
        0x0000000000050800l, // G8
        0x0000000000020400l // H8
    };
    
    public static final long[] MAPS_FILE = { 
        0x8080808080808080l, // FILE_A
            0x4040404040404040l, // FILE_B
            0x2020202020202020l, // FILE_C
            0x1010101010101010l, // FILE_D
            0x0808080808080808l, // FILE_E
            0x0404040404040404l, // FILE_F
            0x0202020202020202l, // FILE_G
            0x0101010101010101l // FILE_H
    };
    public static final long[] MAPS_RANK = { 
        0xFF00000000000000l, // RANK_1
            0x00FF000000000000l, // RANK_2
            0x0000FF0000000000l, // RANK_3
            0x000000FF00000000l, // RANK_4
            0x00000000FF000000l, // RANK_5
            0x0000000000FF0000l, // RANK_6
            0x000000000000FF00l, // RANK_7
            0x00000000000000FFl // RANK_8
    };
    public static final int RANK_1 = 0;
    public static final int RANK_2 = 1;
    public static final int RANK_3 = 2;
    public static final int RANK_4 = 3;
    public static final int RANK_5 = 4;
    public static final int RANK_6 = 5;
    public static final int RANK_7 = 6;
    public static final int RANK_8 = 7;

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
                        if (file == 2 && rank == 1 ||
                            file == 2 && rank == -1 ||
                            file == -2 && rank == 1 ||
                            file == -2 && rank == -1 ||
                            file == 1 && rank == 2 ||
                            file == 1 && rank == -2 ||
                            file == -1 && rank == 2 ||
                            file == -1 && rank == -2) {
                            move += 1;
                        }
                    }
                }
                String res = Long.toHexString(move).toUpperCase();
                while (res.length() < 16) {
                    res = "0" + res;
                }
                System.out.println("0x" + res
                        + "l, // " + (char) ('A' + j) + (i + 1));
                moves[i][j] = move;
            }
        }
        return moves;
    }

    public static void main(final String[] args) {
        compute();
    }
}
