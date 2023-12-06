package org.example;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        ListCashR cashR = new ListCashR();
        int inputUser = 0; // ввод пользователя

        System.out.println("Добро пожаловать в Кассу Аэрофлота!" +
                            "\nПереводим вас в интерактивное меню.");
        do {

                System.out.println("\n\t#--Доступные команды--#" + "\n");

                System.out.println("[1] - Добавить новый рейс" + "\n" +
                        "[2] - Просмотр рейсов в системе" + "\n" +
                        "[3] - Добавление рейсов в файл" + "\n" +
                        "[4] - Удаление рейса из системы" + "\n" +
                        "[5] - Добавление данных из файла в систему" + "\n" +
                        "[6] - Выход из системы");

                System.out.println("\nВведите нужную цифру команды:");

            try{inputUser = scanner.nextInt();}
            catch (Exception e) {
                System.out.println("Ошибка ввода, повторите ввод.");
                scanner.nextLine();
            }
            switch (inputUser) {
                case 1 -> {
                    CashRegister cashROne = new CashRegister();
                    scanner.nextLine();

                    System.out.println("[1] Введите номер рейса");
                    cashROne.setNumflight(scanner.nextLine());

                    System.out.println("[2] Введите маршрут рейса");
                    cashROne.setTrip(scanner.nextLine());

                    System.out.println("[3] Введите пункт промежуточной посадки");
                    cashROne.setStopoverPoints(scanner.nextLine());

                    System.out.println("[4] Введите время отправления");
                    cashROne.setTimeFlight(scanner.nextLine());

                    System.out.println("[5] Введите дни отправления");
                    cashROne.setDayOfflight(scanner.nextLine());

                    System.out.println("[6] Введите количество свободных мест");
                    cashROne.setNumAvblSeatsFl(scanner.nextLine());


                    if (cashR.getListCashData() == null) {
                        ArrayList<CashRegister> temp = new ArrayList<>();
                        temp.add(cashROne);
                        cashR.setListCashData(temp);
                    } else {
                        cashR.getListCashData().add(cashROne);
                    }
                }
                case 2 -> {
                    String line;

                    try {
                        if (cashR.getListCashData() == null) {
                            System.out.println("  *Консольные данные еще не были введены.");
                        } else {
                            System.out.println("  *Не сохраненные файлы в системе:");
                            cashR.getListCashData().forEach(System.out::println);
                        }
                    } catch (Exception ignored){}

                    finally {
                        BufferedReader reader = new BufferedReader(new FileReader("CashFlight.json"));

                        line = reader.readLine();
                        if (line.isEmpty()) {
                            System.out.println("  *Данные не занесены в файл");
                        } else {
                            System.out.println("  *Cохраненные в файл данные:");
                            line = reader.readLine();
                            while (line != null) {
                                System.out.println(line);
                                line = reader.readLine();
                            }
                        }
                            reader.close();
                    }
                }

                case 3 -> {
                    Gson gson = new Gson();
                    String str = gson.toJson(cashR);

                    File file = new File("CashFlight.json");

                    try (FileWriter fileWriter = new FileWriter(file)) {
                        fileWriter.write(str);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Данные добавлены в файл!");
                }

                case 4 -> {
                    System.out.println("Введите маршрут рейса");
                    String search;

                    scanner.nextLine();
                    search = scanner.nextLine();

                    for (CashRegister i: cashR.getListCashData()) {
                        if (i.getTrip().equals(search)){
                            System.out.println("Такой рейс не найден\n" + i);
                            cashR.getListCashData().remove(i);
                            System.out.println("Рейс был удален");
                        } else System.out.println("Рейс не найден");
                    }
                }

                case 5 -> {
                    Gson gson = new Gson();
                    String line = "";

                    BufferedReader reader = new BufferedReader(new FileReader("CashFlight.json"));
                    line = reader.readLine();

                    while(line != null) {
                        line = reader.readLine();
                    }
                    CashRegister cashRegisterG = gson.fromJson( line, CashRegister.class);
                    try{
                        cashRegisterG.getTimeFlight();
                    } catch (Exception e) {
                        System.out.println("В файле отсутсвуют данные.");
                    }
                    reader.close();
                }

                case 6 -> System.out.println("Спасибо что пользовались нашей системой!\n" +
                                            "До свидания!");
            }

        } while(inputUser != 6 );
    }


}