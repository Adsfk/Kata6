package kata6;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Kata6 {
    public static void main(String[] args) {
        File file = new File("C:/Users/Álvaro/Desktop/ASO");
        Iterator<Integer> iterator = megabyteSize(sizeIterator(iteratorOf(file.listFiles())));
        Histogram<Integer> histo = new Histogram<>();
        while(iterator.hasNext()) histo.increment(iterator.next());
        new HistogramDisplay(histo).execute();
    }

    private static Iterator<Integer> megabyteSize(Iterator<Long> iterator) {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Integer next() {
                return (int)(iterator.next()/(1024*1024));
            }
        };
    }

    private static Iterator<Long> sizeIterator(Iterator<File> iterator) {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Long next() {
                return iterator.next().length();
            }
        };
    }


    private static Iterator<File> iteratorOf(File[] files){
        if(files==null) return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public File next() {
                return null;
            }
        };

        return new Iterator<>() {
            private List<File> list = recursive(files);
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < list.size();
            }

            @Override
            public File next() {
                return list.get(index++);
            }
        };
    }

    private static List<File> recursive(File[] files) {
        List<File> list = new ArrayList<>();
        for (File file : files) fill(list, file);
        return list;
    }

    private static void fill(List<File> list, File file) {
        if(file == null) return;
        if(!file.exists()) return;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if(files==null) return;
            for (File child : files) {
                fill(list, child);
            }
        }
        else {
            list.add(file);
        }
    }
}
