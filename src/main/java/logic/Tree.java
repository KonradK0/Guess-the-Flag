package logic;

import io.CsvInputReader;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Tree {
    private List<Node> nodes = new ArrayList<>();
    public List<Flag> flags = initFlags();

    public Tree() {
        nodes.add(new Node("Czy flaga zawiera kolor zielony?", Flag::hasGreen));
        nodes.add(new Node("Czy flaga zawiera kolor czerwony?", Flag::hasRed));
        nodes.add(new Node("Czy flaga zawiera kolor niebieski?", Flag::hasBlue));
        nodes.add(new Node("Czy flaga zawiera kolor czarny?", Flag::hasBlack));
        nodes.add(new Node("Czy flaga zawiera kolor bialy?", Flag::hasWhite));
        nodes.add(new Node("Czy flaga zawiera kolor zolty?", Flag::hasYellow));
        nodes.add(new Node("Czy flaga zawiera kolor brazowy?", Flag::hasBrown));
        nodes.add(new Node("Czy flaga ma dokladnie dwa kolory?", f -> f.getColorsCount() == 2));
        nodes.add(new Node("Czy flaga ma wiecej niz dwa kolory?", f -> f.getColorsCount() > 2));
        nodes.add(new Node("Czy flaga ma dokladnie trzy kolory?", f -> f.getColorsCount() == 3));
        nodes.add(new Node("Czy flaga ma wiecej niz trzy kolory?", f -> f.getColorsCount() > 3));
        nodes.add(new Node("Czy flaga ma dokladnie cztery kolory?", f -> f.getColorsCount() == 4));
        nodes.add(new Node("Czy flaga zawiera godlo panstwa?", Flag::hasCrest));
        nodes.add(new Node("Czy flaga zawiera gwiazde?", Flag::hasStar));
        nodes.add(new Node("Czy flaga zawiera polksiezyc?", Flag::hasHalfMoon));
        nodes.add(new Node("Czy flaga zawiera kolo?", Flag::hasCircle));
        nodes.add(new Node("Czy flaga zawiera krzyz?", Flag::hasCross));
        nodes.add(new Node("Czy flaga zawiera trojkat?", Flag::hasTriangle));
        nodes.add(new Node("Czy flaga ma poziome pasy?", Flag::hasHorizontalStripes));
        nodes.add(new Node("Czy flaga ma pionowe pasy?", Flag::hasVerticalStripes));
        nodes.add(new Node("Czy flaga zawiera kolor pomaranczowy?", Flag::hasOrange));
        nodes.add(new Node("Czy flaga zawiera herb lub kolo?", flag -> flag.hasCircle() || flag.hasCrest()));
        nodes.add(new Node("Czy flaga ma dokladnie dwa pasy (pionowe, poziome lub ukosne)?", f -> f.getStripesCount() == 2));
        nodes.add(new Node("Czy flaga ma dokladnie trzy pasy (pionowe, poziome lub ukosne)?", f -> f.getStripesCount() == 3));
        nodes.add(new Node("Czy flaga ma wiecej niz trzy pasy (pionowe, poziome lub ukosne)?", f -> f.getStripesCount() > 3));
        nodes.add(new Node("Czy flaga ma cztery lub więcej pasów (pionowe, poziome lub ukosne)?", f -> f.getStripesCount() >= 4));
        nodes.add(new Node("Czy flaga ma wiecej niz szesc pasow (pionowe, poziome lub ukosne)?", f -> f.getStripesCount() > 6));
        nodes.add(new Node("Czy flaga ma ukosne pasy?", Flag::hasDiagonalStrupes));
        nodes.add(new Node("Czy flaga ma srodek symetrii", Flag::hasCenterOfSymmetry));
        nodes.add(new Node("Czy kolor na samej gorze flagi to bialy? (Nie, jesli więcej niz jeden kolor na gorze)", flag -> "white".equals(flag.getTopMostColor())));
        nodes.add(new Node("Czy kolor na samej górze flagi to czerwony? (Nie, jesli więcej niz jeden kolor na gorze)", flag -> "red".equals(flag.getTopMostColor())));
        nodes.add(new Node("Czy kolor na samej górze flagi to zielony? (Nie, jesli więcej niz jeden kolor na gorze)", flag -> "green".equals(flag.getTopMostColor())));
        nodes.add(new Node("Czy flaga ma dokladnie jedna gwiazde?", flag -> flag.getStarsCount() == 1));
        nodes.add(new Node("Czy stosunek wysokosci do szerokosci flagi wynosi 2:3?", flag -> "2:3".equals(flag.getWidthHeightRatio())));
        nodes.add(new Node("Czy odcien niebieskiego to blekit?", flag -> "azure".equals(flag.getBlueHue())));
        nodes.add(new Node("Czy odcien niebieskiego to granat?", flag -> "navy".equals(flag.getBlueHue())));
        nodes.add(new Node("Czy odcien niebieskiego to indygo?", flag -> "indygo".equals(flag.getBlueHue())));
        nodes.add(new Node("Czy kolor po prawej stronie flagi to czerwony? (Nie, jesli więcej niz jeden kolor z prawej strony)", flag -> "red".equals(flag.getColorOnRight())));
        nodes.add(new Node("Czy kolor po prawej stronie flagi to zielony? (Nie, jesli więcej niz jeden kolor z prawej strony)", flag -> "green".equals(flag.getColorOnRight())));
        nodes.add(new Node("Czy kolor po prawej stronie flagi to niebieski? (Nie, jesli więcej niz jeden kolor z prawej strony)", flag -> "blue".equals(flag.getColorOnRight())));
        nodes.add(new Node("Czy flaga ma dokladnie 6 gwiazdek?", flag -> flag.getStarsCount() == 6));
        nodes.add(new Node("Czy flaga ma znaki szczegolne? \n (Inne niz godlo, gwiazda, polskiezyc, kolo, krzyz lub trojkat)", Flag::hasSpecialFeatures));
        nodes.add(new Node("Czy dominujacy kolor flagi to czerwony? ", flag -> "red".equals(flag.getDominatingColor())));
        nodes.add(new Node("Czy dominujacy kolor flagi to zielony? ", flag -> "green".equals(flag.getDominatingColor())));
        nodes.add(new Node("Czy dominujacy kolor flagi to niebieski? ", flag -> "blue".equals(flag.getDominatingColor())));
    }


    private List<Flag> initFlags() {
        System.out.println(Paths.get("").toAbsolutePath().toString());
        CsvInputReader csvInputReader = new CsvInputReader();
        List<Flag> flags = csvInputReader.parseFlags();
        flags.forEach(f -> System.out.println(f.name));
        return flags;
    }

    public Node findOptimalNode() {
        Node min = null;
        min = nodes.stream().min(Comparator.comparingDouble(n -> n.calcSplitRatio(flags))).orElse(null);
        min.used = true;
        return min;
    }

    public void filterFlags(Predicate<Flag> predicate) {
        this.flags = this.flags.stream().filter(predicate).collect(Collectors.toList());
    }
}
