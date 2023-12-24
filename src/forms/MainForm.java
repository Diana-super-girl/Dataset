package forms;

import code.animals.Bird;
import code.animals.Cheetah;
import code.animals.SeaDog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class MainForm extends JFrame {

    private static final int UPDATE_RATE_MS = 40;
    private GraphicsPanel graphicsPanel1;
    private JPanel mainPanel;
    private JButton btnAirAnimal;
    private JButton btnGroundAnimal;
    private JButton btnUnderwaterAnimal;
    private JButton btnClear;
    private JLabel lBirdCount;
    private JLabel lCheetahCount;
    private JLabel lSeaDogCount;
    private int birdCount;
    private int cheetahCount;
    private int seaDogCount;
    private final Random random;

    public MainForm(String header) {
        // Инициализация формы
        super(header);
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setMinimumSize(mainPanel.getSize());

        // center on the screen
        setLocationRelativeTo(null);



        random = new Random();
        setUpSpawnButtons();
        setUpClearBtn();

        Timer timer = new Timer(UPDATE_RATE_MS, this::update);
        timer.start();
    }

    // Метод вызывается каждые 40 миллисекунд (через таймер)
    private void update(ActionEvent event) {
        for (var animal : graphicsPanel1.animals) {
            animal.doMovement((double) UPDATE_RATE_MS / 1000);
        }

        graphicsPanel1.repaint(); // отрисовываем графику, чтобы были видны изменения
    }

    private void setUpSpawnButtons() {
        // Кнопка +Стриж
        btnAirAnimal.addActionListener(e -> {
            int min = 30;
            int max = 51;
            float speed = random.nextInt(max - min) + min;// рандом от 30 до 50
            graphicsPanel1.animals.add(new Bird(0, 0, speed));

            birdCount++;
            lBirdCount.setText(String.valueOf(birdCount)); // Счетчик аистов на форме
        });

        // Кнопка +Заяц
        btnGroundAnimal.addActionListener(e -> {
            int min = 1;
            int max = 9;
            float speed = random.nextInt(max - min) + min;// рандом от 1 до 8
            min = 1;
            max = 4;
            float jumpHeight = random.nextInt(max - min) + min;// рандом от 1 до 3
            graphicsPanel1.animals.add(new Cheetah(0, 0, speed, jumpHeight));

            cheetahCount++;
            lCheetahCount.setText(String.valueOf(cheetahCount)); // Счетчик гепардов на форме
        });

        // Кнопка + Дельфин
        btnUnderwaterAnimal.addActionListener(e -> {
            int min = 12;
            int max = 19;
            float speed = random.nextInt(max - min) + min;// рандом от 12 до 18
            min = 2;
            max = 11;
            float motionRange = random.nextInt(max - min) + min;// рандом от 2 до 10
            min = 0; max = 21;
            float maxDepth = random.nextInt(max - min) + min;     // рандом от 0 до 20
            graphicsPanel1.animals.add(new SeaDog(0, 0, speed, motionRange, maxDepth));

            seaDogCount++;
            lSeaDogCount.setText(String.valueOf(seaDogCount)); // Счетчик собачек на форме
        });
    }

    private void setUpClearBtn() {
        btnClear.addActionListener(e -> {
            graphicsPanel1.animals.clear();

            birdCount = 0;
            cheetahCount = 0;
            seaDogCount = 0;

            lBirdCount.setText("0");
            lCheetahCount.setText("0");
            lSeaDogCount.setText("0");
        });
    }
}
