package launcher;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import view.SingleFlagDisplay;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FlagsDisplay extends Application {

    @Override
    public void start(Stage stage) {
        stage.setMaximized(true);
        TilePane tilePane = new TilePane();
        tilePane.setMaxWidth(Region.USE_PREF_SIZE);
        tilePane.setPadding(new Insets(20, 10, 20, 0));
        tilePane.setHgap(10.0);
        tilePane.setPrefColumns(17);
        try {
            Path flagsDir = Paths.get(getClass().getResource("/flagi").toURI());
            for (Path flagFile : Files.list(flagsDir).collect(Collectors.toList())) {
                Image flagImage = new Image("file:" + flagFile.toString());
                ImageView flagIV = setUpImageView(flagImage);
                flagIV.setOnMouseClicked(event -> {
                    SingleFlagDisplay singleFlagDisplay = new SingleFlagDisplay(flagImage);
                    singleFlagDisplay.displayFlag(stage);
                });
                tilePane.getChildren().add(flagIV);
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(tilePane);
        ScrollPane scrollPane = new ScrollPane(hbox);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        stage.setTitle("ImageView");
        stage.setScene(new Scene(scrollPane, 800, 600));
        stage.show();
    }

    private ImageView setUpImageView(Image image){
        ImageView flagIV = new ImageView();
        flagIV.setImage(image);
        flagIV.setFitWidth(100);
        flagIV.setPreserveRatio(true);
        flagIV.setSmooth(true);
        flagIV.setCache(true);
        return flagIV;
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}