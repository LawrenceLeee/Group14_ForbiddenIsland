package edu.bdic.forbiddenisland.util;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ImageFactory {
    // 现有按 ImageType 缓存的部分…
    private static final Map<ImageType, Image> cacheByType = new EnumMap<>(ImageType.class);

    // 新增：按文件名缓存
    private static final Map<String, Image> cacheByName = new HashMap<>();

    /** 旧方法，按枚举类型加载一次并缓存 */
    public static Image get(ImageType type) {
        return cacheByType.computeIfAbsent(type, t ->
                new Image(Objects.requireNonNull(ImageFactory.class.getResourceAsStream(
                        "/edu/bdic/forbiddenisland/images/" + t.getFilename()
                )))
        );
    }

    /** 新方法：按具体文件名加载并缓存 */
    public static Image get(String filename) {
        String path = "/edu/bdic/forbiddenisland/images/" + filename;
        InputStream is = ImageFactory.class.getResourceAsStream(path);
//        System.out.println("Loading image: " + path + " → stream is " + (is != null));
        if (is == null) {
            throw new RuntimeException("Cannot load image resource: " + path);
        }
        return new Image(is);
    }
}
