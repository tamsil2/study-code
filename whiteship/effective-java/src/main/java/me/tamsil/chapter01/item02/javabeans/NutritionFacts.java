package me.tamsil.chapter01.item02.javabeans;

// 코드 2-2 자바빈즈 패턴 - 일관성이 깨지고, 불변으로 만들 수 없다. (16쪽)
public class NutritionFacts {
    // 필드 (기본값이 있다면) 기본값으로 초기화된다.
    private int servingSize = -1;   // (mL, 1회 제공량)  필수
    private int servings = -1;      // (회, 총 n회 제공량) 필수
    private int calories = 0;      // (1회 제공량당)  선택
    private int fat = 0;           // (g/1회 제공량)  선택
    private int sodium = 0;        // (mg/1회 제공량)  선택
    private int carbohydrate = 0;  // (g/1회 제공량)  선택

    public NutritionFacts() {
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public static void main(String[] args) {
        NutritionFacts cocaCola = new NutritionFacts();
        cocaCola.setServingSize(240);
        cocaCola.setServings(8);

        cocaCola.setCalories(100);
        cocaCola.setSodium(52);
        cocaCola.setCarbohydrate(27);
    }
}
