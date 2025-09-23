package vo;

public class Foundation extends Product{
    String texture;

    public Foundation() {
    }

    public Foundation(String texture) {
        this.texture = texture;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }
}
