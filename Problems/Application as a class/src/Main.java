class Application {

    String name;

    void run(String[] args) {
        // implement
        System.out.println(this.name);
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}