package com.second.algorithm.programmers;

import java.util.Arrays;

public class Solution_60060_가사_검색 {
    public static void main(String[] args) {
        String[] words = {"frodo", "xrodo", "frost", "frozen", "frame", "kakao"};
//        String[] queries = {"fro??", "???ao", "fr???", "fro???", "pro?"};
        String[] queries = {"?rodo"};

        int[] rst = solution(words, queries);
        Arrays.stream(rst).forEach(System.out::println);
    }

    private static int[] solution(String[] words, String[] queries) {
        // 단어 정렬
        Arrays.sort(words, ((o1, o2) -> customComparator(o1, o2)));

        // 역순 탐색 위해 새로운 배열 만들어 정렬
        String[] reverseWord = makeReverseWords(words);

        // 가사 검색 시작
        int order = 0;
        int[] match = new int[queries.length];
        for (String query : queries) {
            int leftLengthIndex = findLeftIndexByLength(query.length(), words);
            int rightLengthIndex = findRightIndexByLength(query.length(), words);

            // 매칭되는 길이의 문자열이 없는 경우
            if (rightLengthIndex < leftLengthIndex) match[order++] = 0;
            else {
                // 쿼리가 와일드카드만있는 경우
                if (query.startsWith("?") && query.endsWith("?")) {
                    match[order++] = rightLengthIndex - leftLengthIndex + 1;
                    continue;
                }

                boolean isPrefix = !query.startsWith("?");
                String queryString = parseString(query, isPrefix);

                // 첫번재 인덱스와 마지막 인덱스를 구해서 개수 구해주기
                int firstIndex = findFirstIndexWord(queryString, leftLengthIndex, rightLengthIndex, isPrefix ? words : reverseWord);
                int lastIndex = findLastIndexWord(queryString, leftLengthIndex, rightLengthIndex, isPrefix ? words : reverseWord);

                // 마지막 인덱스가 첫번째 인덱스보다 큰 경우 일치하는 문자열 없음
                if (lastIndex < firstIndex) match[order++] = 0;
                else match[order++] = lastIndex - firstIndex + 1;
            }
        }

        return match;
    }

    private static int findLastIndexWord(String queryString, int left, int right, String[] words) {
        // 문자 이분 탐색
        while (left <= right) {
            int mid = (left + right) / 2;

            // substring을 통해 비교 words[mid]의 접두사가 queryString과 동일하거나 사전 순서에서 더 앞에 있는 경우 오른쪽 탐색
            if (words[mid].substring(0, queryString.length()).compareTo(queryString) <= 0) left = mid + 1;
            else right = mid - 1;

        }
        return right;
    }

    private static int findFirstIndexWord(String queryString, int left, int right, String[] words) {
        // 문자를 가지고 이분탐색
        while (left < right) {
            int mid = (left + right) / 2;

            // substring을 통해 비교 words[mid]의 접두사와 queryString과 동일하거나 사전 순서에서 더 뒤에 있는 경우 왼쪽 탐색
            if (words[mid].substring(0, queryString.length()).compareTo(queryString) >= 0) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    // 와일드카드 제거해서 쿼리스트링 만들기
    private static String parseString(String query, boolean isPrefix) {
        String[] parseString = query.split("\\?");

        if (isPrefix) return parseString[0];

        // 접미사의 경우 문자열을 뒤집어 비교
        StringBuilder str = new StringBuilder(parseString[parseString.length - 1]);
        return str.reverse().toString();
    }

    private static int findRightIndexByLength(int length, String[] words) {
        int left = 0;
        int right = words.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            // 마지막 길이 인덱스 찾는 작업  = 동일한 길이인 경우 오른쪽 탐색
            if (words[mid].length() <= length) left = mid + 1;
            else right = mid - 1;
        }

        return right;
    }

    private static int findLeftIndexByLength(int length, String[] words) {
        int left = 0;
        int right = words.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            // 첫번째 길이 인덱스 찾는 작업이기 때문에 동일한 길이인 경우에는 왼쪽 탐색
            if (words[mid].length() >= length) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private static int customComparator(String o1, String o2) {
        // 길이 동일한 경우 단어 정렬
        if (o1.length() == o2.length()) return o1.compareTo(o2);

        // 길이 기준 정렬
        return Integer.compare(o1.length(), o2.length());
    }

    // 역순 문자열 저장
    private static String[] makeReverseWords(String[] words) {
        String[] reverseWord = new String[words.length];

        int index = 0;
        for (String word : words) {
            StringBuilder str = new StringBuilder(word);
            reverseWord[index++] = str.reverse().toString();
        }

        Arrays.sort(reverseWord, (o1, o2) -> customComparator(o1, o2));

        return reverseWord;
    }
}
