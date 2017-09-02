package nara.share.util.uri;


public class UriUtil {

    // TODO RegExpression
    public static boolean match(String uri, String pattern) {
        //
        if (pattern == null || uri == null) return false;
        if (uri.startsWith("/")) uri = uri.substring(1);
        if (uri.endsWith("/")) uri = uri.substring(0, uri.length() - 1);
        if (pattern.startsWith("/")) pattern = pattern.substring(1);
        if (pattern.endsWith("/")) pattern = pattern.substring(0, pattern.length() - 1);

        String[] uriSplit = uri.split("/");
        String[] patternSplit = pattern.split("/");

        if (uriSplit.length != patternSplit.length) return false;
        for(int i = 0 ; i < uriSplit.length ; i++) {
            if (patternSplit[i].startsWith("{") && patternSplit[i].endsWith("}")) continue;
            if (!uriSplit[i].equals(patternSplit[i])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        String uri = "board-api/boards/17-0001-001/postings/17-0001-001-0002/replies/";
        String pattern = "/board-api/boards/{boardId}/postings/{postingId}/replies";

        System.out.println(UriUtil.match(uri, pattern));

    }
}
