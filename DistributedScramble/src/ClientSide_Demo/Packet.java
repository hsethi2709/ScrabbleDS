package ClientSide_Demo;

public class Packet<Content> {

    private String header;
    private Content content;
    private String username;

    public Packet(String header, Content content,String username) {
        this.header = header;
        this.content = content;
        this.username=username;
    }

    public String getHeader() {
        return this.header;
    }

    public Content getContent() {
        return this.content;
    }
    public String getUsername() {
    	return this.username;
    }

}
