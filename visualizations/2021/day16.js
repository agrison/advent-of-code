// sum
// let STACK = '1100001000000000101101000000101010000010';
// let STEPS = [
//     {action: 'Version', pointerBefore: 0, value: 6, pointerAfter: 3},
//     {action: 'Packet Type ID', pointerBefore: 3, value: 0, pointerAfter: 6},
//     {action: 'Number of sub packets', pointerBefore: 6, pointerAfter: 7},
//     {action: 'Version', pointerBefore: 18, value: 6, pointerAfter: 21},
//     {action: 'Packet Type ID', pointerBefore: 21, value: 4, pointerAfter: 24},
//     {action: 'readPacket', pointerBefore: 24, value: 1, packet: '00001', pointerAfter: 29},
//     {action: 'Version', pointerBefore: 29, value: 2, pointerAfter: 32},
//     {action: 'Packet Type ID', pointerBefore: 32, value: 4, pointerAfter: 35},
//     {action: 'readPacket', pointerBefore: 35, value: 2, packet: '00010', pointerAfter: 40},
//     {action: 'Sum', subPackets: [1, 2], value: 3},

// product
// let STACK = '000001000000000001011010110000110011100010010000';
// let STEPS = [
//     {action: 'Version', pointerBefore: 0, value: 0, pointerAfter: 3},
//     {action: 'Packet Type ID', pointerBefore: 3, value: 1, pointerAfter: 6},
//     {action: 'Length Type ID', pointerBefore: 6, value: 'Total Length', pointerAfter: 7},
//     {action: 'Version', pointerBefore: 22, value: 5, pointerAfter: 25},
//     {action: 'Packet Type ID', pointerBefore: 25, value: 4, pointerAfter: 28},
//     {action: 'Read packet', pointerBefore: 28, value: 6, packet: '00110', pointerAfter: 33},
//     {action: 'Version', pointerBefore: 33, value: 3, pointerAfter: 36},
//     {action: 'Packet Type ID', pointerBefore: 36, value: 4, pointerAfter: 39},
//     {action: 'Read packet', pointerBefore: 39, value: 9, packet: '01001', pointerAfter: 44},
//     {action: 'Product', subPackets: [6, 9], value: 54},
// ]

// min
let STACK = '10001000000000001000011011000011111010001000000100010010';
let STEPS = [
    {action: 'Version', pointerBefore: 0, value: 4, pointerAfter: 3},
    {action: 'Packet Type ID', pointerBefore: 3, value: 2, pointerAfter: 6},
    {action: 'Length Type ID', pointerBefore: 6, value: 'Total Length', pointerAfter: 7},
    {action: 'Version', pointerBefore: 22, value: 5, pointerAfter: 25},
    {action: 'Packet Type ID', pointerBefore: 25, value: 4, pointerAfter: 28},
    {action: 'Read packet', pointerBefore: 28, value: 7, packet: '00111', pointerAfter: 33},
    {action: 'Version', pointerBefore: 33, value: 6, pointerAfter: 36},
    {action: 'Packet Type ID', pointerBefore: 36, value: 4, pointerAfter: 39},
    {action: 'Read packet', pointerBefore: 39, value: 8, packet: '01000', pointerAfter: 44},
    {action: 'Version', pointerBefore: 44, value: 0, pointerAfter: 47},
    {action: 'Packet Type ID', pointerBefore: 47, value: 4, pointerAfter: 50},
    {action: 'Read packet', pointerBefore: 50, value: 9, packet: '01001', pointerAfter: 55},
    {action: 'Min', subPackets: [7, 8, 9], value: 7},
]

// let STACK = '10011100000000010100000100001000000000100101000000110010000011110001100000000010000100000100101000001000'
// let STEPS = [
// {action: 'Version', pointerBefore: 0, value: 4, pointerAfter: 3},
// {action: 'Packet Type ID', pointerBefore: 3, value: 7, pointerAfter: 6},
// {action: 'Length Type ID', pointerBefore: 6, value: 'Total Length', pointerAfter: 7},
// {action: 'Version', pointerBefore: 22, value: 2, pointerAfter: 25},
// {action: 'Packet Type ID', pointerBefore: 25, value: 0, pointerAfter: 28},
// {action: 'Length Type ID', pointerBefore: 28, value: 'Number of sub packets', pointerAfter: 29},
// {action: 'Version', pointerBefore: 40, value: 2, pointerAfter: 43},
// {action: 'Packet Type ID', pointerBefore: 43, value: 4, pointerAfter: 46},
// {action: 'Read packet', pointerBefore: 46, value: 1, packet: '00001', pointerAfter: 51},
// {action: 'Version', pointerBefore: 51, value: 4, pointerAfter: 54},
// {action: 'Packet Type ID', pointerBefore: 54, value: 4, pointerAfter: 57},
// {action: 'Read packet', pointerBefore: 57, value: 3, packet: '00011', pointerAfter: 62},
// {action: 'Sum', subPackets: [1, 3], value: 4},
// {action: 'Version', pointerBefore: 62, value: 6, pointerAfter: 65},
// {action: 'Packet Type ID', pointerBefore: 65, value: 1, pointerAfter: 68},
// {action: 'Length Type ID', pointerBefore: 68, value: 'Number of sub packets', pointerAfter: 69},
// {action: 'Version', pointerBefore: 80, value: 0, pointerAfter: 83},
// {action: 'Packet Type ID', pointerBefore: 83, value: 4, pointerAfter: 86},
// {action: 'Read packet', pointerBefore: 86, value: 2, packet: '00010', pointerAfter: 91},
// {action: 'Version', pointerBefore: 91, value: 2, pointerAfter: 94},
// {action: 'Packet Type ID', pointerBefore: 94, value: 4, pointerAfter: 97},
// {action: 'Read packet', pointerBefore: 97, value: 2, packet: '00010', pointerAfter: 102},
// {action: 'Product', subPackets: [2, 2], value: 4},
// {action: 'Equals', subPackets: [4, 4], value: 1}
//     ]


let VISITED = " ".repeat(STACK.length)
let SUBPACKETS = new Set();

function setup() {
    frameRate(60);
    createCanvas(499 * 4, 499);
}

let N = 0;

function depthColor(x) {
    const w = {
        0: 'rgb(21, 34, 56)',
        1: 'rgb(25, 40, 65)',
        2: 'rgb(28, 46, 74)',
        3: 'rgb(32, 51, 84)',
        4: 'rgb(35, 57, 93)',
        5: 'rgb(48,74,114)',
        6: 'rgb(71,101,147)',
        7: 'rgb(89,119,167)',
        8: 'rgb(107,137,186)',
        9: 'rgb(148,179,227)',
    }
    return w[x] || 'black';
}

let K = 0;
let DONE = false;
let J = 0;


function action(step) {
    if (['Sum',
        'Product',
        'GreaterThan',
        'LowerThan',
        'Equals'].includes(step.action)) {
        stroke("#00c8ff");
        fill("#00c8ff")

        let left = Array.from(SUBPACKETS).map(e=>e.split('/')[1])[0]
        text(left, 50, 100);

        stroke('rgb(255, 255, 102)');
        fill('rgb(255, 255, 102)')
        text({'Sum': ' + ', 'Product': ' * ', 'GreaterThan': ' > ', 'LowerThan': ' < ', 'Equals': ' = '}[step.action], textWidth(left) + 50, 100);

        let right = Array.from(SUBPACKETS).map(e=>e.split('/')[1])[1]
        stroke("#00cc00");
        fill("#00cc00")
        text(right, textWidth(left + "   ") + 50, 100);

        stroke(depthColor(9));
        fill(depthColor(9))
        text("=", textWidth(left + "    " + right) + 50, 100);

        stroke('rgb(255, 255, 102)');
        fill('rgb(255, 255, 102)')
        text(step.value, textWidth(left + "   =  " + right ) + 50, 100);
    }
    else if (['Min',
            'Max'].includes(step.action)) {
        let left = Array.from(SUBPACKETS).map(e=>e.split('/')[0])
        let right = Array.from(SUBPACKETS).map(e=>e.split('/')[1])

        text(`${step.action}(${Array.from(SUBPACKETS).map(e=>e.split('/')[1])}) =`, 50, 100)

        stroke('rgb(255, 255, 102)');
        fill('rgb(255, 255, 102)')
        text(`${step.value}`, 50 + textWidth(`${step.action}(${Array.from(SUBPACKETS).map(e=>e.split('/')[1])}) = `), 100)
        }
    else {
        text(step.action + ': ', 50, 100)
        stroke('rgb(255, 255, 102)');
        fill('rgb(255, 255, 102)')
        text(step.value, 50 + textWidth(step.action + ': '), 100)
    }

    if (step.pointerBefore != null && step.pointerAfter) {
        VISITED = VISITED.substring(0, step.pointerBefore) + STACK.substring(step.pointerBefore, step.pointerAfter) + VISITED.substring(step.pointerAfter);
        textSize(42);
        stroke('gray');
        fill('gray')
        text(VISITED, 30, 40)

        stroke('rgb(255, 255, 102)');
        fill('rgb(255, 255, 102)')
        let leftPad = textWidth("0".repeat(step.pointerBefore));
        rect(30 + leftPad,
            55,
            textWidth("0".repeat(step.pointerAfter - step.pointerBefore)),
            5);

        text(STACK.substring(step.pointerBefore, step.pointerAfter), 30 + leftPad, 40)
    }

    stroke(depthColor(9));
    fill(depthColor(9))

    if (step.packet) {
        textSize(30);
        text('Packet: ' + step.packet + 'b -> ' + step.value, 50, 140)
        SUBPACKETS.add(`${step.packet}/${step.value}`)
    }

    textSize(25);
    let Z = Array.from(SUBPACKETS)
    for (i = 0; i <Z.length; ++i) {
        stroke(depthColor(9));
        fill(depthColor(9))

        text('\tSub Packet '+ i + ': ' + Z[i].split('/')[0] + 'b -> ', 50, 170 + i * 30)
        let color = (i == 0) ? "#00c8ff" : ((i == 1) ?"#00cc00" : "#ff0000")
        stroke(color);
        fill(color)
        text(Z[i].split('/')[1], 50 + textWidth('\tSub Packet '+ i + ': ' + Z[i].split('/')[0] + 'b ->  '), 170 + i * 30)
    }
}

function draw() {
    background("#10101a");
    stroke(depthColor(9));
    fill(depthColor(9))
    textSize(42);
    textFont('Consolas');
    text(STACK, 30, 40);

    textSize(30);
    let step = STEPS[Math.min(N, STEPS.length - 1)]
    action(step)

    if (K++ > 50) {
        K = 0
        N++
    }
}