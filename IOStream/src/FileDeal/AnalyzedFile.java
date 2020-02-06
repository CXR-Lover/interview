package FileDeal;

import java.io.File;

public class AnalyzedFile {
    public void FileDeal() {
        File file = new File("");
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return super.loadClass(name);
            }
        };
    }
}
