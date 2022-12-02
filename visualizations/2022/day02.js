let INPUT = ("A Y\n" +
    "B X\n" +
    "B X\n" +
    "C Y\n" +
    "B X\n" +
    "A Z\n" +
    "B X\n" +
    "B X\n" +
    "C Z\n" +
    "A Z\n" +
    "C Z\n" +
    "A X\n" +
    "A Y\n" +
    "A Y\n" +
    "B X\n" +
    "C Y\n" +
    "B Z\n" +
    "A X\n" +
    "B X\n" +
    "C Y");

function setup() {
    frameRate(60);
    createCanvas(450, 400);
}

let N = 1000;

function textyellow() {
    stroke('rgb(204, 204, 204)');
    fill('rgb(204, 204, 204)');
}

function textblue() {
    stroke('lightblue');
    fill('lightblue');
}

let rock, paper, scissors;

function preload() {
    rock = loadImage('rock.png');
    paper = loadImage('paper.png');
    scissors = loadImage('scissors.png');
}

const L = INPUT.split("\n");

const WIDTH = 100;

function drawLeft(y, icon) {
    if (icon == 'A') {
        image(rock, 50, y, WIDTH, WIDTH);
    } else if (icon == 'B') {
        image(paper, 50, y, WIDTH, WIDTH);
    } else if (icon == 'C') {
        image(scissors, 50, y, WIDTH, WIDTH);
    }
}

function drawRight(y, icon) {
    if (icon == 'X') {
        image(rock, 250, y, WIDTH, WIDTH);
    } else if (icon == 'Y') {
        image(paper, 250, y, WIDTH, WIDTH);
    } else if (icon == 'Z') {
        image(scissors, 250, y, WIDTH, WIDTH);
    }
}

N = 0;
let U = 0;
let score1 = 0;
let score2 = 0;

let shapeScores = {'X': 1, 'Y': 2, 'Z': 3};

let outcomes = {
    "A X": 3, "B Y": 3, "C Z": 3,
    "A Y": 6, "B Z": 6, "C X": 6
};

let roundEnding = {
    "A X": 'Z', "B X": 'X', "C X": 'Y',
    "A Y": 'X', "B Y": 'Y', "C Y": 'Z',
    "A Z": 'Y', "B Z": 'Z', "C Z": 'Z',
};

function draw() {
    background("#10101a");
    textyellow();

    stroke('rgb(204, 204, 204)');
    fill('rgb(255, 255, 102)')
    textSize(18);
    textFont('Consolas');
    text('Strategy #1: ' + score1, 50, 30);

    var pair = L[N].split(" ");
    drawLeft(50, pair[0]);
    drawRight(50, pair[1]);
    text('Strategy #2: ' + score2, 50, 220);

    var txt2 = pair[0] + " " + roundEnding[L[N]];
    var pair2 = txt2.split(" ");
    drawLeft(250, pair2[0]);
    drawRight(250, pair2[1]);

    text('Round ' + (N + 1), 350, 30);
    if (N != L.length - 1 && U++ > 20) {
        N++;
        if (N > L.length - 1) {
            N = L.length - 1;
        }
        U = 0;
        score1 += (outcomes[L[N]] || 0) + (shapeScores[pair[1]] || 0);
        score2 += (outcomes[txt2] || 0) + (shapeScores[pair2[1]] || 0);
    }

}