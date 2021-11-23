public class Main {

    public static void main(String[] args) {
        String INPUT_STRING = "КРОК/task_6_2/src/./../../task_6_1/../../../мемы/котики";

        PathNormalizer pathNormalizer = new PathNormalizer(INPUT_STRING); // задаем путь
        pathNormalizer.normalize(); // нормализуем строку
        System.out.println(pathNormalizer);
    }
}
