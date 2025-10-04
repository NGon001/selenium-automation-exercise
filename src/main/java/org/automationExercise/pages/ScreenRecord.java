package org.automationExercise.pages;

import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class ScreenRecord extends ScreenRecorder {
    public static ScreenRecord screenRecorder;
    public static String recordingDir;
    public static boolean isInitialized = false;
    private String testName;

    public ScreenRecord(GraphicsConfiguration cfg, File file, String testName) throws Exception {
        super(cfg, null, new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                null, null, file);
        this.testName = testName;
    }

    @Override
    protected File createMovieFile(Format fileFormat) {
        String timeStamp = new SimpleDateFormat("HH-mm-ss").format(new Date());
        return new File(recordingDir, testName + " " + timeStamp + ".avi");
    }

    public static void startRecording(String testName) throws Exception {
        if (!isInitialized) {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            recordingDir = "videos/" + timeStamp;
            File folder = new File(recordingDir);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            isInitialized = true;
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle captureSize = new Rectangle(0, 0, screenSize.width, screenSize.height);

        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        File folder = new File(recordingDir);
        screenRecorder = new ScreenRecord(gc, folder, testName);
        screenRecorder.start();

        System.out.println("🎥 Recording started for test: " + testName);
    }

    public static void stopRecording() throws Exception {
        screenRecorder.stop();
        System.out.println("✅ Recording stopped. Saved in folder: " + recordingDir);
    }
}
