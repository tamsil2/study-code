package chapter09;

public class StrategyMain {
    public static void main(String[] args) {
        // 이전 방법
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaaa");
        System.out.println("b1 : " + b1);
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("bbbb");
        System.out.println("b2 : " + b2);

        // 람다를 직접 전달
        Validator numericValidator2 = new Validator((String s) -> s.matches("[a-z]"));
        boolean b3 = numericValidator2.validate("aaaa");
        System.out.println("b3 : " + b3);
        Validator lowerCaseValidator2 = new Validator((String s) -> s.matches("\\d+"));
        boolean b4 = lowerCaseValidator2.validate("bbbb");
        System.out.println("b4 : " + b4);
    }

    public interface ValidationStrategy {
        boolean execute(String s);
    }

    private static class IsAllLowerCase implements ValidationStrategy {
        @Override
        public boolean execute(String s) {
            return s.matches("[a-z]+");
        }
    }

    private static class IsNumeric implements ValidationStrategy {
        @Override
        public boolean execute(String s) {
            return s.matches("\\d+");
        }
    }

    private static class Validator {
        private final ValidationStrategy strategy;

        public Validator(ValidationStrategy v) {
            this.strategy = v;
        }
        public boolean validate(String s) {
            return strategy.execute(s);
        }
    }
}
