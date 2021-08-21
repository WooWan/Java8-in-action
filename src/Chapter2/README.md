최근 스프링을 공부하고 있는데, 1학년 때 배운 자바 지식으로는 부족함이 많이 느껴졌다. 자바 8 in action이라는 책을 추천 받아 공부하고 있는데, 자바8 이후의 변경에 대해 주로 설명하고 있고 단순히 문법만 정리한 책이 아니고 좋은 java 코드란 어떤것인지 고민할 수 있어 자바 개발자로서 한 걸음 성장하기 위해 블로그에 잘 정리해놔야겠다.

Chapter1 은 전체적인 개요여서 Chapter2부터 시작합니다.

## Chapter 2 동적 파라미터화 코드 전달하기

예를 들어 녹색 사과만 필터링하는 아래와 같은 코드가 있다고 하자
```java
public static List<Apple> filterGreenApples(List<Apple> inventory){
	List<Apple> result = new ArrayList<>();
	for(Apple apple : inventory){
    	if("green".equals(apple.getColor()){
        	result.add(apple);
        }
     }
}
```
하지만 요구사항이 변해서 빨간 사과를 필터링 한다면 아래와 같이 메소드를 바꾸어야 한다. 
```java
public static List<Apple> filterGreenApples(List<Apple> inventory){
	List<Apple> result = new ArrayList<>();
	for(Apple apple : inventory){
    	if("red".equals(apple.getColor()){
        	result.add(apple);
        }
     }
}
```
너무 비효율적이지 않은가? 비슷한 코드가 있다면 추상화하는 것이 좋은 선택이다. 중복 코드를 최대한 안 만드는 것이 DRY 관점에서 좋은 선택이다
```java
public static List<Apple> filterGreenApples(List<Apple> inventory, String color){
	List<Apple> result = new ArrayList<>();
	for(Apple apple : inventory){
    	if(color.equals(apple.getColor()){
        	result.add(apple);
        }
     }
}
```

여기까지는 좋다. 하지만 요구사항이 추가되어 무게로 필터링하게 된다면, filter 인터페이스를 만든 뒤, 동작 파라미터화를 해서 런타임에 implement룰 결정하는 것도 좋은 방법이다.

### 동작 파라미터화
```java
public interface ApplePredicate{
    boolean test(Apple apple);
}

public class AppleHeavyWeightPredicate implements ApplePredicate{
    boolean test(Apples apple){
    	return apple.getWeight() >150;
    }
}
```
이를 통해 test 메소드를 객체로 감싸 전달 할 수 있다.

### 익명 클래스 사용
인터페이스를 사용하는 것은 분명 좋은 방법이지만, 인터페이스 구현과 실행 클래스를 만들 때 많은 코드를 작성해야하는 tradeoff가 존재한다. 익명 클래스를 통해서도 이를 해결할 수 있지만, 익명 클래스는 참조를 어디에서 하냐에 따라 값이 바뀔 수 있어서 한 눈에 코드를 파악하기 어렵다. 더더욱 많은 사람들이 같이 참여하는 프로젝트 같은 경우는 오류가 굉장히 발생하기 쉬울 것 같다. 

더 나은 방법으로는 람다식을 활용할 수 있다.
### 람다식 활용

```java
List<Apple> result = filter(inventory, (Apple apple) -> "Red".equals(apple.getColor()));
```
람다식은 함수형 프로그래밍과 밀접한 관련이 있는데 람다식을 활용한 남은 chapter들이 너무 기대가 된다..ㅎㅎㅎ

### 실전 예제
자바에서는 list를 정렬하기 위해 Comparator interface를 활용할 수 있다

```java
inventory.sort(new Comparator<Apple>(){
    public int compare(Apple a1, Apple a2){
    	return a1.getWeight().compareTo(a2.getWeight());
    }
}

람다식을 활용
inventory.sort(
	(Apple a1, Apple a2) -> a.getWeight().compareTo(a2.getWeight()));
```
자바로 알고리즘을 공부할 때 위와 같은 comparator 사용하는 것이 길어서 코드 작성하기 불편하였는데 람다식을 미리 알았더라면,,, 얼마나 좋았을까


### 마치며
오늘은 동작 파라미터화와 람다식을 공부하였는데 뒤에서 이를 활용해 stream, functional programming이 너무 기대된다..ㅎㅎ핳
