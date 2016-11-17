package org.parabot.api.misc;

/**
 * @author EmmaStone
 */
public class TextUtils {

    private static final char[] validChars = {
            '_', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2',
            '3', '4', '5', '6', '7', '8', '9'
    };

    public static long longForName(String name) {
        long l = 0L;
        for (int i = 0; i < name.length() && i < 12; i++) {
            char c = name.charAt(i);
            l *= 37L;
            if (c >= 'A' && c <= 'Z') {
                l += (1 + c) - 65;
            } else if (c >= 'a' && c <= 'z') {
                l += (1 + c) - 97;
            } else if (c >= '0' && c <= '9') {
                l += (27 + c) - 48;
            }
        }

        while (l % 37L == 0L && l != 0L) {
            l /= 37L;
        }

        return l;
    }

    public static String nameForLong(long name) {
        try {
            if (name <= 0L || name >= 0x5b5b57f8a98a5dd1L) {
                return "invalid_name";
            }
            if (name % 37L == 0L) {
                return "invalid_name";
            }

            int i = 0;
            char ac[] = new char[12];
            while (name != 0L) {
                long l1 = name;
                name /= 37L;
                ac[11 - i++] = validChars[(int) (l1 - name * 37L)];
            }
            return new String(ac, 12 - i, i);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String fixName(String name) {
        if (name.length() > 0) {
            char ac[] = name.toCharArray();
            for (int j = 0; j < ac.length; j++)
                if (ac[j] == '_') {
                    ac[j] = ' ';
                    if (j + 1 < ac.length && ac[j + 1] >= 'a' && ac[j + 1] <= 'z') {
                        ac[j + 1] = (char) ((ac[j + 1] + 65) - 97);
                    }
                }

            if (ac[0] >= 'a' && ac[0] <= 'z') {
                ac[0] = (char) ((ac[0] + 65) - 97);
            }
            return new String(ac);
        } else {
            return name;
        }
    }
}