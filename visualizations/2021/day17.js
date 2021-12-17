let W = 1500
let H = 1200

function setup() {
    frameRate(24);
    createCanvas(W, H);
}

let K = 0;
let DONE = false;
let J = 0;

let tx1=20;
let tx2=30;
let ty1=-10
let ty2=-5;

function plot(ivx, ivy) {
    let thismaxy = 0;
    let probex = 0;
    let probey = 0;
    let vx = ivx;
    let vy = ivy;
    let points = [{probex:0, probey:0, vx, vy, step:0, inside:false}];
    for (let i = 1; i <= ty1 * -2; ++i) {
        probex += vx;
        probey += vy;
        vx -= Math.sign(vx);
        vy -= 1;
        if (probey > thismaxy) thismaxy = probey;
        if (probex >= tx1 && probex <= tx2 && probey >= ty1 && probey <= ty2) {
            points.push({probex, probey, step:i, inside:true, vx, vy});
        } else {
            points.push({probex, probey, step:i, inside:false, vx, vy});
        }
    }
    return points;
}

let PLOTS = [];

for (let ivx = 0; ivx <= tx2; ++ivx) {
    for (let ivy = ty1; ivy <= -ty1; ++ivy) {
        let zz = plot(ivx, ivy)
        PLOTS.push(zz)
    }
}
let RATIO = 3

function translateX(x) {
    return x + 30// + W/4
}

function translateY(y) {
    return -y + 75// + H - 20
}


let N = 0

function draw() {
    background("#10101a");

    let U = Math.min(N, PLOTS.length -1)
    let V = Math.max(0, U - 50)
;stroke('#10101a')
    fill('white')
    textSize(20)
    text("Velocities with probe inside target: ", 10, 25)
    text("Max Y: ", 10, 50)

    stroke('white')
    for (I = V; I < U; I++) {
        if (PLOTS[I].filter(e => e.inside).length == 0) {
            stroke('rgb(48,74,114)');
            fill('rgb(48,74,114)')
            for (i = 0; i < PLOTS[I].length; ++i) {
                let p = PLOTS[I][i]
                // console.log(translateX(p.probex), translateY(p.probey))
                arc(translateX(p.probex)*RATIO, translateY(p.probey)*RATIO, 1*RATIO, 1*RATIO, 0, TWO_PI);
            }
        }
    }

    let COUNT = 0;
    let MAXY = 0;
    for (I = 0; I < U; I++) {
        if (PLOTS[I].filter(e => e.inside).length > 0) {
            COUNT++;
            // console.log(I) // 170
            // text(I, 500, 100)
            stroke('rgb(255, 255, 102)');
            fill('rgb(255, 255, 102)')
            for (i = 0; i < PLOTS[I].length; ++i) {
                let p = PLOTS[I][i]
                if (p.probey > MAXY) MAXY = p.probey
                // console.log(translateX(p.probex), translateY(p.probey))
                arc(translateX(p.probex)*RATIO, translateY(p.probey)*RATIO, 1, 1, 0, TWO_PI);
            }
        }
    }

    text(COUNT, textWidth("Velocities with probe inside target: ")+ 20, 25)
    text(MAXY, textWidth("Max Y: ")+ 20, 50)

    stroke('white');
    fill('white')
    arc(translateX(0)*RATIO, translateY(0)*RATIO, 2*3*RATIO, 2*3*RATIO, 0, TWO_PI);

    stroke('rgb(255, 255, 102)');
    fill('rgba(89,119,167,0.1)')
    rect(translateX(20)*RATIO, translateY(-10)*RATIO, 10*RATIO, -5*RATIO)

N+=4
}