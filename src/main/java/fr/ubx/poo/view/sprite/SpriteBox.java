package fr.ubx.poo.view.sprite;

import fr.ubx.poo.model.go.character.Monster;
import fr.ubx.poo.view.image.ImageFactory;
import fr.ubx.poo.view.image.ImageResource;
import javafx.scene.layout.Pane;
import fr.ubx.poo.model.go.character.Box;

public class SpriteBox extends SpriteGameObject {

    public SpriteBox(Pane layer,Box box) {
        super(layer, null, box);
        updateImage();
    }


    @Override
    public void updateImage() {
        Box Box = (Box) go;
        setImage(ImageFactory.getInstance().get(ImageResource.BOX));
    }
}
