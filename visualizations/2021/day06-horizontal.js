let N = 0;
let H;

function setup() {
    frameRate(60);
    createCanvas(940, 570);
    H = makeH();
}

function makeH() {
    const H = [BigInt(0), BigInt(0), BigInt(0), BigInt(0), BigInt(0), BigInt(0), BigInt(0), BigInt(0), BigInt(0)];
    "3,5,1,5,3,2,1,3,4,2,5,1,3,3,2,5,1,3,1,5,5,1,1,1,2,4,1,4,5,2,1,2,4,3,1,2,3,4,3,4,4,5,1,1,1,1,5,5,3,4,4,4,5,3,4,1,4,3,3,2,1,1,3,3,3,2,1,3,5,2,3,4,2,5,4,5,4,4,2,2,3,3,3,3,5,4,2,3,1,2,1,1,2,2,5,1,1,4,1,5,3,2,1,4,1,5,1,4,5,2,1,1,1,4,5,4,2,4,5,4,2,4,4,1,1,2,2,1,1,2,3,3,2,5,2,1,1,2,1,1,1,3,2,3,1,5,4,5,3,3,2,1,1,1,3,5,1,1,4,4,5,4,3,3,3,3,2,4,5,2,1,1,1,4,2,4,2,2,5,5,5,4,1,1,5,1,5,2,1,3,3,2,5,2,1,2,4,3,3,1,5,4,1,1,1,4,2,5,5,4,4,3,4,3,1,5,5,2,5,4,2,3,4,1,1,4,4,3,4,1,3,4,1,1,4,3,2,2,5,3,1,4,4,4,1,3,4,3,1,5,3,3,5,5,4,4,1,2,4,2,2,3,1,1,4,5,3,1,1,1,1,3,5,4,1,1,2,1,1,2,1,2,3,1,1,3,2,2,5,5,1,5,5,1,4,4,3,5,4,4"
        .split(",")
        .map(i => parseInt(i))
        .forEach(i => {
            H[i]++;
        });
    return H;
}

let K = 50;

function draw() {
    background("#10101a");
    stroke('rgb(204, 204, 204)');
    strokeWeight(0.2);

    // elapse
    fill("rgb(204, 204, 204)");
    textSize(32)
    text("days: " + N, 10, 30);

    // compute
    H = makeH();
    for (t = 0; t < N && t < 256; ++t) {
        H[(t + 7) % 9] += H[t % 9];
    }

    // find max and sum
    let M = BigInt(0)
    let S = BigInt(0)
    for (t = 0; t < 9; ++t) {
        if (H[t] > M) M = H[t];
        S += BigInt(H[t]);
    }
    if (M === 0n)
        M = 1n

    // render

    for (t = 0; t < 9; ++t) {
        // Index in white
        fill("rgb(204, 204, 204)");
        textSize(20)
        text(t, 50, 80 + t * 50);

        let X = 100n * H[t];
        X = X / M;
        let Z = Number(X);

        // yellow border rectangle
        fill("#10101a");
        stroke('rgb(255, 255, 102)');
        strokeWeight(2.5)
        rect(80, 50 + t * 50, 8 * Z, 40);

        // fish count
        strokeWeight(1)
        fill(255, 255, 102);
        textSize(20)
        text(H[t].toLocaleString(), 100, 77 + t * 50);
    }

    // total fishes
    fill("rgb(204, 204, 204)");
    textSize(20)
    text("Fishes: " + S.toLocaleString(), 50, 530);

    if (N === 80) { // pause at 80 for part 1
        if (K-- < 0) N++;
    } else if (N < 256) N++;
}