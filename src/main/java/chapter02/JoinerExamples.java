package chapter02;

import com.google.common.base.Joiner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class JoinerExamples {

    public static final Joiner stringJoiner = Joiner.on("|").skipNulls();

    public static String buildString(List<String> stringList, String delimiter) {
        StringBuilder builder = new StringBuilder();
        for (String s: stringList) {
            if (s != null) {
                builder.append(s).append(delimiter);
            }
        }
        builder.setLength(builder.length() - delimiter.length());
        return builder.toString();
    }

    public static String buildStringWithJoiner(List<String> stringList, String delimiter) {
        return Joiner.on(delimiter).skipNulls().join(stringList);
    }

    public static String buildStringWithJoiner(List<String> stringList, String delimiter, String userForNull) {
        return Joiner.on(delimiter).useForNull(userForNull).join(stringList);
    }

    public static StringBuilder appendToStringBuilder(String...args) {
       return stringJoiner.appendTo(new StringBuilder(), args);
    }

    public static FileWriter appendToFileWriter(String path, List<LocalDate> dateList) {
        try (FileWriter fileWriter = new FileWriter(new File(path))) {
            return Joiner.on("#").useForNull(" ").appendTo(fileWriter, dateList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
