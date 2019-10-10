package wil.liam.demo.mylibrary;import java.security.MessageDigest;import java.util.regex.Matcher;import java.util.regex.Pattern;public class StringUtils {    public static boolean isEmpty(String s) {        return s == null || s.length() == 0;    }    public static boolean areEqual(final String a, final String b) {        return areEqual(a, b, false);    }    private static boolean areEqual(String a, String b, boolean ignoreCase) {        if (a == null)            return b == null;        return b != null && (ignoreCase ? (a.compareToIgnoreCase(b) == 0) : a.equals(b));    }    public static String stringToMd5(String string){        if (isEmpty(string))            return "";        try {            final byte[] byteBuffer = string.getBytes("UTF-8");            final MessageDigest md5 = MessageDigest.getInstance("MD5");            return bufferToHex(md5.digest(byteBuffer));        }catch (Throwable t){            return "";        }    }    public static String stringToExt(final String string,final String defExt) {        String ext = defExt;        final int index = string.lastIndexOf('.');        if (index != -1)            ext = string.substring(index+1);        return ext;    }    public static String hashToStirng(byte[] hashBytes) {        String returnVal = "";        for (int i = 0; i< hashBytes.length; i++) {            returnVal += Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1);        }        return returnVal.toLowerCase();    }    private static String bufferToHex(byte digest[]) {        return bufferToHex(digest,0,digest.length);    }    private static String bufferToHex(byte digest[], int m, int n) {        StringBuffer stringBuffer = new StringBuffer(2*n);        int k = m+n;        for (int l = m; l < k; l++)            appendHexPair(digest[l],stringBuffer);        return stringBuffer.toString();    }    protected static char[] hexDigits = {            '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'    };    private static void appendHexPair(byte b, StringBuffer stringBuffer) {        char c0 = hexDigits[(b & 0xf0) >> 4];        char c1 = hexDigits [b & 0xf];        stringBuffer.append(c0);        stringBuffer.append(c1);    }    public static String replaceBlank(String str){        String dest = "";        if (str != null){            Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");            Matcher matcher = pattern.matcher(str);            dest = matcher.replaceAll("");        }        return dest;    }}