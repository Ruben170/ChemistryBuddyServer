package quiz.server.logic;

import quiz.server.interfaces.iQuiz;
import quiz.server.model.Question;
import quiz.server.model.QuestionDictionary;

import java.util.Dictionary;

public class QuizLogic implements iQuiz {
    @Override
    public Question getRandomQuestion() {
        QuestionDictionary questionDictionary = new QuestionDictionary();
        Dictionary questions = questionDictionary.getQuestions();
        ShuffleLogic shuffleLogic = new ShuffleLogic();
        return (Question) questions.get(shuffleLogic.shuffleNumbers(questions.size()));
    }

    @Override
    public boolean checkAnswer(Question question, String answer) {
        return question.Answer.equals(answer);
    }
}
