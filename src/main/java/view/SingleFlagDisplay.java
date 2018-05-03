package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.Node;
import logic.Tree;

public class SingleFlagDisplay {

    final private ImageView flag = new ImageView();
    final Stage popupFlag = new Stage();
    final Text questionTextField = new Text();
    final Text counterTextField = new Text();
    private final Button yes = new Button("Tak");
    private final Button no = new Button("Nie");
    private final Tree tree;
    private Node currentNode;
    private int counter = 0;

    public SingleFlagDisplay(Image flagImage) {
        flag.setImage(flagImage);
        flag.setFitWidth(500);
        flag.setPreserveRatio(true);
        flag.setSmooth(true);
        flag.setCache(true);
        tree = new Tree();
    }

    public void displayFlag(Stage primaryStage) {
        popupFlag.initModality(Modality.APPLICATION_MODAL);
        popupFlag.initOwner(primaryStage);
        GridPane pane = new GridPane();
        pane.add(flag, 0, 0);
        currentNode = tree.findOptimalNode();
        this.questionTextField.setText(currentNode.question);
        setButtonsOnClicks();
        pane.add(questionTextField, 0, 1);
        pane.add(counterTextField, 0, 2);
        TilePane tilePane = new TilePane();
        tilePane.getChildren().addAll(yes, no);
        pane.add(tilePane, 0, 3);
        Scene popupScene = new Scene(pane, 600, 600);
        popupFlag.setScene(popupScene);
        popupFlag.show();
    }

    public void setButtonsOnClicks() {
        yes.setOnMouseClicked(event -> {
            tree.filterFlags(currentNode.predicate);
            counter++;
            //if(currentNode == null){
                System.out.print("Możliwe kraje to: ");
                tree.flags.forEach(f -> System.out.print(f.name + ", "));
                //return;
            //}
            if(tree.flags.size() < 2){
                questionTextField.setText("Kraj o ktorym myslisz to " + tree.flags.get(0).name);
                counterTextField.setText("Liczba krokow = " + counter);
                System.out.println(counter);
                return;
            }
            currentNode = tree.findOptimalNode();
            questionTextField.setText(currentNode.question);
        });
        no.setOnMouseClicked(event -> {
            counter++;
            tree.filterFlags(currentNode.predicate.negate());
            if(tree.flags.size() <2){
                questionTextField.setText("Kraj o ktorym myslisz to " + tree.flags.get(0).name);
                counterTextField.setText("Liczba kroków = " + counter);
                return;
            }
            currentNode = tree.findOptimalNode();
            questionTextField.setText(currentNode.question);
        });
    }
}
