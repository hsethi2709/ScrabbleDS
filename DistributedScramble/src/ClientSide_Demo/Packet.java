package ClientSide_Demo;

public class Packet<Content> {

    private String header;
    private Content content;

    public Packet(String header, Content content) {
        this.header = header;
        this.content = content;
    }

    public String getHeader() {
        return this.header;
    }

    public Content getContent() {
        return this.content;
    }

}
