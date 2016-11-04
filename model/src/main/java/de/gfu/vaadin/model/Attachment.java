package de.gfu.vaadin.model;

/**
 * Created by mbecker on 29.07.2016.
 */
public class Attachment extends Item {

    private byte[] data;

    private String filename;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
