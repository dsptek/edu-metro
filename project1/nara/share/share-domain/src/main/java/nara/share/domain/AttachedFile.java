package nara.share.domain;

public class AttachedFile implements ValueObject {
    //
    private String attachmentId;
    private String fileName;
    private String contentType;
    private Long contentLength;

    public AttachedFile() {
        //
    }

    public AttachedFile(String attachmentId, String fileName) {
        //
        this.attachmentId = attachmentId;
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("attachmentId:'").append(attachmentId).append('\'');
        sb.append(", fileName:'").append(fileName).append('\'');
        sb.append(", contentType:'").append(contentType).append('\'');
        sb.append(", contentLength:").append(contentLength);
        sb.append('}');
        return sb.toString();
    }

    public static AttachedFile getSample() {
        //
        String attachmentId = "1";
        String fileName = "Hello.zip";
        AttachedFile sample = new AttachedFile(attachmentId, fileName);

        return sample;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getContentLength() {
        return contentLength;
    }

    public void setContentLength(Long contentLength) {
        this.contentLength = contentLength;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }

}
