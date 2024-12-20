package Quiz1;

import java.util.*;
import java.util.Random;
import java.util.Scanner;



class Question {
    String questionText;
    String[] options;
    int correctAnswer;

    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public void displayQuestion(boolean[] showOptions) {
        System.out.println(questionText);
        for (int i = 0; i < options.length; i++) {
            if (showOptions[i]) {
                System.out.println((i + 1) + ". " + options[i]);
            }
        }
    }

    public boolean isCorrect(int userChoice) {
        return userChoice == correctAnswer;
    }
}

public class QuizApplication1 {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("WELCOME TO OUR QUIZ COMPETITION");
        Thread.sleep(2000);

        System.out.println("Enter the name:");
        String name = scanner.nextLine();
        Thread.sleep(1000);

        System.out.println("Enter the ID:");
        int id = scanner.nextInt();
        Thread.sleep(1000);

        System.out.println("Enter the mail ID:");
        String email = scanner.next();
        Thread.sleep(1000);

        System.out.println("Enter your place name:");
        String place = scanner.next();
        Thread.sleep(1000);

        System.out.println("Enter your pincode:");
        int pincode = scanner.nextInt();
        Thread.sleep(1000);

        System.out.println("\nPlayer Details:");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Email: " + email);
        System.out.println("Place: " + place);
        System.out.println("Pincode: " + pincode);

        System.out.println("\nDo you agree to participate in the quiz? (YES/NO):");
        String playerChoice = scanner.next();
        if (!playerChoice.equalsIgnoreCase("YES")) {
            System.out.println("Terminating the game. Thank you!");
            return;
        }

        System.out.println("\n           RULES          ");
        System.out.println("1. Provide correct details.");
        System.out.println("2. Select an option within the time.");
        System.out.println("3. Lifelines can be used only once.");
        System.out.println("4. There are three lifelines available.");
        System.out.println("Follow the rules and enjoy the game!");
try {
        // Questions
        ArrayList<Question> quiz = new ArrayList<>();
        quiz.add(new Question("Who is the Prime Minister of India?",
                new String[]{"Chandra Babu", "Jagan", "Narendra Modi", "Pawan Kalyan", "Quit", "Lifeline"}, 3));
        quiz.add(new Question("In Bahubali movie, who is the main actor?",
                new String[]{"Rana", "Prabhas", "Kattappa", "Anushka", "Quit", "Lifeline"}, 2));
        quiz.add(new Question("Who represents India internationally?",
                new String[]{"Salman Khan", "Akshay Kumar", "Aamir Khan", "Shah Rukh Khan", "Quit", "Lifeline"}, 4));
        quiz.add(new Question("Who is the director of the Bahubali movie?",
                new String[]{"SS Rajamouli", "Trivikram", "Sukumar", "RGV", "Quit", "Lifeline"}, 1));

        int score = 0;
        boolean lifelineUsed = false;

                for (int i = 0; i < quiz.size(); i++) {
            Question currentQuestion = quiz.get(i);
            boolean[] showOptions = {true, true, true, true, true, true};

            System.out.println("\nQuestion " + (i + 1) + ":");
            currentQuestion.displayQuestion(showOptions);

            System.out.print("Enter your choice (1-6): ");
            int userChoice = scanner.nextInt();

            if (userChoice == 5) {
                System.out.println("Exiting... Thank you for playing!");
                break;
            }

            if (userChoice == 6) {
                if (lifelineUsed) {
                    System.out.println("Lifeline already used! Choose a valid option.");
                    i--;
                    continue;
                }
                System.out.println("Lifeline Options:");
                System.out.println("1. Swap the question");
                System.out.println("2. Audience Poll");
                System.out.println("3. 50-50");
                System.out.print("Choose a lifeline (1-3): ");
                int lifelineChoice = scanner.nextInt();

                switch (lifelineChoice) {
                    case 1:
                        int randomIndex = random.nextInt(quiz.size());
                        System.out.println("Question swapped!");
                        i = randomIndex - 1;
                        break;

                    case 2:
                        int[] poll = new int[4];
                        poll[currentQuestion.correctAnswer - 1] = random.nextInt(41) + 60; // 60-100% for correct
                        int remaining = 100 - poll[currentQuestion.correctAnswer - 1];
                        int allocated = 0;

                        for (int j = 0; j < poll.length; j++) {
                            if (j != currentQuestion.correctAnswer - 1) {
                                int allocation = j < 2 ? random.nextInt(Math.min(remaining - allocated, 30)) : remaining - allocated;
                                poll[j] = allocation;
                                allocated += allocation;
                            }
                        }

                        System.out.println("Audience poll results:");
                        for (int j = 0; j < poll.length; j++) {
                            System.out.println("Option " + (j + 1) + ": " + poll[j] + "%");
                        }
                        break;
                    case 3:
                       System.out.println("50-50 Lifeline Activated!");
                        boolean[] fiftyOptions = {false, false, false, false};
                        fiftyOptions[currentQuestion.correctAnswer - 1] = true;
                        int randomWrong;
                        do {
                            randomWrong = random.nextInt(4);
                        } while (randomWrong == currentQuestion.correctAnswer - 1);
                        fiftyOptions[randomWrong] = true;
                        currentQuestion.displayQuestion(fiftyOptions);
                        break;

                    default:
                        System.out.println("Invalid lifeline choice.");
                        i--;
                        break;
                }
                lifelineUsed = true;
                i--;
                continue;
            }

            if (currentQuestion.isCorrect(userChoice)) {
                System.out.println("Correct!");
                score++;
                System.out.println("Score is :"+score);
            } else {
                System.out.println("Wrong! The correct answer is Option " + currentQuestion.correctAnswer + ".");
                System.out.println("Game over. Your final score is: " + score);
                break;
            }

            if (i == quiz.size() - 1) {
                System.out.println("\nCongratulations! You completed the quiz.");
                System.out.println("Your total score is: " + score);
                System.out.println("Congratulations on being certified!");
                System.out.println("Name: " + name);
                System.out.println("ID: " + id);
                System.out.println("Email: " + email);
                System.out.println("Place: " + place);
                System.out.println("Pincode: " + pincode);
                
                System.out.println("**********THANK YOU***********");
            }
        }
}
catch(Exception e) {
	System.out.println(e+"Exception occured");
}
        scanner.close();
    }
}