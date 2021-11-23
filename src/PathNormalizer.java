import java.util.*;

/**
 * В виде строки задан относительный путь в файловой системе, в котором:
 * "." означает текущую директорию;
 * ".." означает родительскую директорию по отношению к текущей;
 * "/" используется в качестве разделителя директорий.
 *
 * Реализовать функцию, выполняющую "нормализацию" заданного пути,
 * то есть, удаляющую из него лишние директории с учетом переходов "." и "..".
 *
 * Пример:
 * [in]
 * "КРОК/task_6_2/src/./../../task_6_1/../../../мемы/котики"
 *
 * [out]
 * "../мемы/котики"
 */
public class PathNormalizer {
    private String path; // относительный путь в файловой системе в виде строки

    private Deque<String> normalizedDeque = new ArrayDeque<>(); // очередь для хранения нормализованного пути

    // описание символов пути
    private String CUR_DIR_SYMB = "."; // текущая директория
    private String PARENT_DIR_SYMB = ".."; // родительская директория по отношению к текущей
    private String SEPARATOR_DIR_SYMB = "/"; // разделитель директорий

    public PathNormalizer(String path) {
        this.path = path;
    }

    /**
     * Функция, выполняющая "нормализацию" заданного пути,
     * то есть, удаляющая из него лишние директории с учетом переходов "." и "..".
     */
    public void normalize() {
        // разделяем путь при помощи разделителя "/" и вносим в коллекцию list
        List<String> directories = new ArrayList<>(Arrays.asList(path.split(SEPARATOR_DIR_SYMB)));
        directories.removeIf(d -> d.equals(CUR_DIR_SYMB)); // удаляем все "." (т. к. они не играют роли в определении пути)

        // Смотрим каждую директорию:
        for (String dir : directories) {

            if (!normalizedDeque.isEmpty()){ // (если очередь не пустая)
                // если директория = ".." и предыдущая директория = ".."
                if (dir.equals(PARENT_DIR_SYMB) && normalizedDeque.getLast().equals(PARENT_DIR_SYMB)){
                    normalizedDeque.offer(dir); // то добавляем в очередь
                // если директория = ".." (при этом предыдущая директория не равна ".."
                } else if(dir.equals(PARENT_DIR_SYMB)){
                    normalizedDeque.pollLast(); // то удаляем последний элемент очереди (переходим в родительскую директорию)
                } else {
                    normalizedDeque.offer(dir); // добавляем в очередь
                }

            } else { // (пустая очередь)
                normalizedDeque.offer(dir); // добавляем
            }
        }
    }

    @Override
    public String toString() {
        String resToString = "";
        for (String el: normalizedDeque) {
            resToString += el + SEPARATOR_DIR_SYMB;
        }
        return resToString.substring(0, resToString.length() - 1); // возвращаем строку, удалив последний символ "/"
    }
}