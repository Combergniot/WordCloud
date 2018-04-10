import com.google.common.collect.ImmutableSet;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;
import org.apache.log4j.Logger;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        final Logger logger = Logger.getLogger(Main.class.getName());

        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();

        frequencyAnalyzer.setWordFrequenciesToReturn(150);
        frequencyAnalyzer.setMinWordLength(4);
        frequencyAnalyzer.setStopWords(
                ImmutableSet.of("jeżeli", "tylko",
                        "dnia", "będzie", "dobra",
                        "jest", "jeśli", "moich",
                        "roku", "przez", "proszę",
                        "min", "jesteś", "wyrażam",
                        "oraz", "będą", "masz", "poz",
                        "oo", "przy", "poz", "min", "adecco",
                        "które", "101", "zaraz", "wraz", "zm",
                        "również", "naszego", "chcesz", "poz",
                        "związku", "jako", "poprzez", "mojej",
                        "sobie", "swoich", "także",
                        "ponad", "tekst", "pokaż", "więcej",
                        "poz.", "o.o.", "tel.", "min.", "zm.", "zm", "swoje"));


        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load("C:/Baza ofert/olx.txt");
        final Dimension dimension = new Dimension(600, 600);
        final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        wordCloud.setPadding(2);
        wordCloud.setBackground(new CircleBackground(300));
        wordCloud.setBackgroundColor(Color.WHITE);
        wordCloud.setColorPalette(new ColorPalette(
                new Color(0x331c54),
                new Color(0x1a206d),
                new Color(0x5172),
                new Color(0x747a),
                new Color(0, 105, 60),
                new Color(0x69be28),
                new Color(132, 197, 37)));


        wordCloud.setFontScalar(new SqrtFontScalar(10, 70));
        wordCloud.build(wordFrequencies);
        wordCloud.writeToFile("C:/Baza ofert/wordcloud_circle.png");

    }
}
