package com.second.algorithm.programmers;

import java.util.*;

public class Solution_92341_주차_요금_계산 {
    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};

        int[] rst = solution(fees, records);
        for (int x : rst) {
            System.out.println("x = " + x);
        }
    }

    private static int[] solution(int[] fees, String[] records) {
        /* fees :  0 : 기본 시간(분), 1 : 기본 요금, 2 : 단위 시간(분), 3: 단위 요금 */
        /* 기본 요금 + ((주차 시간 - 기본 시간) /  단위 시간 ) * 600 */

        // 주차 기록 남기기
        Map<String, Car> logs = new HashMap<>();
        for (String record : records) {
            // 변수 받기
            StringTokenizer st = new StringTokenizer(record);
            int timeInformation = convertMinute(st.nextToken());
            String carNumber = st.nextToken();
            String status = st.nextToken();

            remainLogs(logs, timeInformation, carNumber, status);
        }

        // 주차 요금 계산하기
        int defaultMinute = fees[0];
        int defaultFee = fees[1];
        int unitMinute = fees[2];
        int unitFee = fees[3];

        List<String> carNumbers = new ArrayList<>(logs.keySet());
        Collections.sort(carNumbers);

        List<Integer> payments = new ArrayList<>();
        for (String carNumber : carNumbers) {
            // 주차장에서 입차상태인 경우 출차를 안했기 때문에 23:59에서 시간 계산을 해준다.
            final String END_TIME = "23:59";
            Car car = logs.get(carNumber);

            int parkingMinute = car.getParkingMinute();
            if(!car.isStatus())
                parkingMinute += (convertMinute(END_TIME) - car.getEntranceTime());

            // 주차 요금 계산
            int fee = defaultFee +  (int) Math.ceil((double) (parkingMinute - defaultMinute) / unitMinute) * unitFee;

            if(parkingMinute <= defaultMinute) fee = defaultFee;
            payments.add(fee);
        }

        return payments.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void remainLogs(Map<String, Car> logs, int timeInformation, String carNumber, String status) {
        // 주차장에 기록이 없는 경우 기록 남기기
        if(!logs.containsKey(carNumber)) {
            logs.put(carNumber, new Car(carNumber, 0, timeInformation, false));
        }
        // 주차장 기록이 있는 경우 출차 기록 판단
        else {
            // 입차
            Car car = logs.get(carNumber);
            if (status.equals("IN")) {

                // 입차의 경우 입차 시간을 갱신한다.
                logs.replace(carNumber, new Car(carNumber, car.getParkingMinute(), timeInformation, false));
            }
            // 출차
            else {
                // 출차의 경우 주차 시간을 갱신한다.
                int parkingMinute = car.getParkingMinute() + (timeInformation - car.getEntranceTime());

                logs.replace(carNumber, new Car(carNumber, parkingMinute, 0, true));
            }
        }
    }

    private static int convertMinute(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        return hour * 60 + minute;
    }

    static class Car {
        private String carNumber;   // 차량번호
        private int parkingMinute;  // 주차시간
        private int entranceTime;   // 입차시간
        private boolean status; // false : 입차 / true : 출차

        public Car(String carNumber, int parkingMinute, int entranceTime, boolean status) {
            this.carNumber = carNumber;
            this.parkingMinute = parkingMinute;
            this.entranceTime = entranceTime;
            this.status = status;
        }

        public String getCarNumber() {
            return carNumber;
        }

        public int getParkingMinute() {
            return parkingMinute;
        }

        public int getEntranceTime() {
            return entranceTime;
        }

        public boolean isStatus() {
            return status;
        }
    }
}
