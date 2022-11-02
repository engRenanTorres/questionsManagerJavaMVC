
const question = document.querySelector('#question');
const choices = Array.from(document.querySelectorAll('.choice-text'));
const progressText = document.querySelector('#progressText');
const scoreText = document.querySelector('#score');
const progressBarFull = document.querySelector('#progressBarFull');

let currentQuestion = {};
let acceptingAnswers = true;
let score = 0;
let questionCounter = 0;
let availableQuestion = [];

let questionAPI=[];

axios
    .get('http://localhost:8080/api/questions/all')
    .then(response => {

    questionAPI = response.data;
    console.log(questionAPI);
    })
    .catch(function (error) {
      // handle error
      console.log(error);
    })
    .finally(function () {
      // always executed
    });







let questions = [
    {
        question: 'Quem é o maior eng do brasil?',
        choice1:'Renan',
        choice2: 'Diego',
        choice3: 'Peçanha',
        choice4: 'Noira',
        choice5: 'Mezenga',
        answer: 1
    },
    {
        question: 'Quem é o mais bonito do brasil?',
        choice1:'Renan',
        choice2: 'Diego',
        choice3: 'Peçanha',
        choice4: 'Noira',
        choice5: 'Mezenga',
        answer: 1
    },
    {
        question: 'Quem é o mais feio do brasil?',
        choice1:'Renan',
        choice2: 'Diego',
        choice3: 'Peçanha',
        choice4: 'Noira',
        choice5: 'Mezenga',
        answer: 4
    },
]

const SCORE_POINTS = 100;
const MAX_QUESTIONS = 3;

startGame = () => {
    questionCounter = 0;
    score = 0;
    availableQuestion = [...questions];
    getNewQuestion();
}

getNewQuestion = () => {
    if (availableQuestion.length === 0 || questionCounter > MAX_QUESTIONS) {
        localStorage.setItem('mostRecentScore',score);
        return window.location.assign('./end.html');
    }
    questionCounter++;
    progressText.innerText = `Questão ${questionCounter} de ${MAX_QUESTIONS}`;
    progressBarFull.style.width = `${(questionCounter/MAX_QUESTIONS) * 100}%`;
    
    const questionsIndex = Math.floor(Math.random() * availableQuestion.length);
    currentQuestion = availableQuestion[questionsIndex];
    question.innerText = currentQuestion.question;

    choices.forEach(choice => {
        const number = choice.dataset['number'];
        choice.innerText = currentQuestion['choice' + number];
    });
    availableQuestion.splice(questionsIndex,1);
    acceptingAnswers = true;
}

choices.forEach(choice => {
    choice.addEventListener('click', e => {
        if(!acceptingAnswers) return;

        acceptingAnswers = false;
        const selectedChoice = e.target;
        const selectedAnswer = selectedChoice.dataset['number'];
        let classToApply = selectedAnswer == currentQuestion.answer ? 'correct' : 'incorrect';
        
        if(classToApply === 'correct') {
            incrementScore(SCORE_POINTS);
        }
        selectedChoice.parentElement.classList.add(classToApply);

        setTimeout(() => {
            selectedChoice.parentElement.classList.remove(classToApply);
            getNewQuestion();
        },1000);
    });
});

incrementScore = num => {
    score +=num;
    scoreText.innerText = score;
}

startGame();