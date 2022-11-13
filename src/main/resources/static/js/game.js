//const axios = require('axios').default;

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
const SCORE_POINTS = 100;
let MAX_QUESTIONS = 1;

async function onLoad() {
    try {
        const response = await axios.get('http://localhost:8080/api/questions/all');
        questions = response.data;
        MAX_QUESTIONS = questions.length;
        console.log(response);
        startGame();
    } catch (error) {
        console.error(error);
    }
}

let result = {
    questionId: 0,
    answerResult: ""
}


let questions = [
    {
        question: 'Quem é o maior eng do brasil?',
        choice1:'Renan',
        choice2: 'Diego',
        choice3: 'Peçanha',
        choice4: 'Noira',
        choice5: 'Mezenga',
        answer: 1
    }
]


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
    question.innerText = currentQuestion.enunciado;

    choices.forEach(choice => {
        const number = choice.dataset['number'];
        choice.innerText = currentQuestion['alternativa' + number];
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
        const answerResult = Number(selectedAnswer)+64 == currentQuestion.resposta.charCodeAt(0);
        let classToApply = answerResult ? 'correct' : 'incorrect';
        //console.log( Number(selectedAnswer)+64 + "" + currentQuestion.resposta.charCodeAt(0) );
        result = {
                questionId: currentQuestion.id,
                answerMarked: String.fromCharCode(Number(selectedAnswer)+64),
                answerResult: answerResult
        }
       axios
          .post('http://localhost:8080/api/results',result)
          .then(response => console.log(response))
          .catch(error => {
            console.log(error.response.data);
          });
        //console.log( result );


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

