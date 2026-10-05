// =====================================================
// SCAMSHIELD - COMMON RISK ENGINE
// =====================================================

function analyzeScamText(text) {

    text = text.toLowerCase();

    let riskScore = 0;
    let indicators = [];
    let tactics = [];
    let scamType = "Suspicious Message";


    // =================================================
    // 1. URGENCY
    // =================================================

    if (
        text.includes("urgent") ||
        text.includes("immediately") ||
        text.includes("now") ||
        text.includes("minutes") ||
        text.includes("within 24 hours")
    ) {

        riskScore += 15;

        indicators.push("Urgency detected");

        tactics.push("⏰ Urgency");
    }


    // =================================================
    // 2. FEAR / THREATS
    // =================================================

    if (
        text.includes("blocked") ||
        text.includes("suspended") ||
        text.includes("closed") ||
        text.includes("warning") ||
        text.includes("penalty")
    ) {

        riskScore += 20;

        indicators.push(
            "Threat or account warning detected"
        );

        tactics.push("😨 Fear / Threat");
    }


    // =================================================
    // 3. SENSITIVE INFORMATION
    // =================================================

    if (
        text.includes("otp") ||
        text.includes("password") ||
        text.includes("pin") ||
        text.includes("cvv")
    ) {

        riskScore += 20;

        indicators.push(
            "Sensitive information request detected"
        );

        tactics.push(
            "🔐 Sensitive Information Request"
        );
    }


    // =================================================
    // 4. LINK / ACTION
    // =================================================

    if (
        text.includes("click") ||
        text.includes("link") ||
        text.includes("verify") ||
        text.includes("open")
    ) {

        riskScore += 15;

        indicators.push(
            "Suspicious action or link detected"
        );
    }


    // =================================================
    // 5. MONEY / REWARD
    // =================================================

    if (
        text.includes("₹") ||
        text.includes("money") ||
        text.includes("payment") ||
        text.includes("prize") ||
        text.includes("won") ||
        text.includes("cashback") ||
        text.includes("reward")
    ) {

        riskScore += 20;

        indicators.push(
            "Financial or reward-related content detected"
        );

        tactics.push("🎁 Reward / Greed");
    }


    // =================================================
    // 6. BANK / ACCOUNT / KYC
    // =================================================

    if (
        text.includes("bank") ||
        text.includes("account") ||
        text.includes("kyc")
    ) {

        riskScore += 10;

        indicators.push(
            "Possible financial-service impersonation"
        );

        tactics.push(
            "🏢 Authority / Impersonation"
        );
    }


    // =================================================
    // 7. JOB SCAM
    // =================================================

    if (
        text.includes("job") ||
        text.includes("salary") ||
        text.includes("work from home") ||
        text.includes("hiring")
    ) {

        scamType = "Fake Job Scam";
    }


    // =================================================
    // 8. PRIZE / LOTTERY SCAM
    // =================================================

    else if (
        text.includes("prize") ||
        text.includes("lottery") ||
        text.includes("won") ||
        text.includes("reward")
    ) {

        scamType = "Prize / Lottery Scam";
    }


    // =================================================
    // 9. INVESTMENT SCAM
    // =================================================

    else if (
        text.includes("investment") ||
        text.includes("profit") ||
        text.includes("crypto") ||
        text.includes("trading")
    ) {

        scamType = "Investment Scam";
    }


    // =================================================
    // 10. BANK / KYC PHISHING
    // =================================================

    else if (
        text.includes("bank") ||
        text.includes("kyc") ||
        text.includes("account") ||
        text.includes("otp")
    ) {

        scamType = "Bank / KYC Phishing";
    }


    // =================================================
    // REMOVE DUPLICATE TACTICS
    // =================================================

    tactics = [...new Set(tactics)];


    // =================================================
    // LIMIT SCORE
    // =================================================

    if (riskScore > 100) {

        riskScore = 100;
    }


    // =================================================
    // RISK LEVEL
    // =================================================

    let riskLevel;

    if (riskScore >= 81) {

        riskLevel = "CRITICAL";
    }

    else if (riskScore >= 61) {

        riskLevel = "HIGH";
    }

    else if (riskScore >= 31) {

        riskLevel = "MEDIUM";
    }

    else {

        riskLevel = "LOW";
    }


    // =================================================
    // RECOMMENDED ACTION
    // =================================================

    let recommendedAction;

    if (riskScore >= 61) {

        recommendedAction =
            "Do not click suspicious links or share passwords, OTPs, PINs, or financial information. Verify the message through an official source.";
    }

    else {

        recommendedAction =
            "Stay cautious and verify the information before taking any action.";
    }


    // =================================================
    // RETURN COMMON RESULT
    // =================================================

    return {

        riskScore: riskScore,

        riskLevel: riskLevel,

        scamType: scamType,

        indicators: indicators,

        tactics: tactics,

        recommendedAction: recommendedAction

    };
}


// =====================================================
// TACTIC EXPLANATIONS
// =====================================================

function getTacticExplanation(tactic) {

    if (tactic.includes("Urgency")) {

        return "The sender is pressuring you to act quickly, reducing the chance that you will verify the message.";
    }

    if (tactic.includes("Fear")) {

        return "The message uses threats or negative consequences to make you act without thinking carefully.";
    }

    if (tactic.includes("Authority")) {

        return "The sender may be pretending to represent a trusted organization or authority.";
    }

    if (tactic.includes("Reward")) {

        return "The message uses money, a prize, or a reward to encourage an unsafe action.";
    }

    if (tactic.includes("Sensitive")) {

        return "The message requests sensitive information such as an OTP, PIN, password, or CVV.";
    }

    return "";
}
// =====================================================
// SCAM ATTACK RECONSTRUCTION
// =====================================================

function generateAttackReconstruction(result) {

    let steps = [];

    // STEP 1 - AUTHORITY / IMPERSONATION
    if (result.tactics.some(tactic =>
        tactic.includes("Authority")
    )) {

        steps.push(`
            <div class="tactic-box">
                <strong>🏦 1. Impersonates a trusted organization</strong>
                <p>
                    The sender may be pretending to represent a bank,
                    company, government service, or another trusted authority.
                </p>
            </div>
        `);
    }


    // STEP 2 - FEAR
    if (result.tactics.some(tactic =>
        tactic.includes("Fear")
    )) {

        steps.push(`
            <div class="tactic-box">
                <strong>😨 ${steps.length + 1}. Creates fear</strong>
                <p>
                    The message introduces a threat or negative consequence
                    to make the user worried.
                </p>
            </div>
        `);
    }


    // STEP 3 - URGENCY
    if (result.tactics.some(tactic =>
        tactic.includes("Urgency")
    )) {

        steps.push(`
            <div class="tactic-box">
                <strong>⏰ ${steps.length + 1}. Creates urgency</strong>
                <p>
                    The sender pressures the user to act quickly
                    without verifying the information.
                </p>
            </div>
        `);
    }


    // STEP 4 - REWARD
    if (result.tactics.some(tactic =>
        tactic.includes("Reward")
    )) {

        steps.push(`
            <div class="tactic-box">
                <strong>🎁 ${steps.length + 1}. Offers a reward</strong>
                <p>
                    The message uses money, prizes, cashback,
                    or another reward to encourage action.
                </p>
            </div>
        `);
    }


    // STEP 5 - LINK / ACTION
    if (
        result.indicators.some(indicator =>
            indicator.includes("action") ||
            indicator.includes("link")
        )
    ) {

        steps.push(`
            <div class="tactic-box">
                <strong>🔗 ${steps.length + 1}. Directs the user to take action</strong>
                <p>
                    The scam attempts to make the user click a link,
                    open something, or perform another action.
                </p>
            </div>
        `);
    }


    // STEP 6 - SENSITIVE INFORMATION
    if (result.tactics.some(tactic =>
        tactic.includes("Sensitive")
    )) {

        steps.push(`
            <div class="tactic-box">
                <strong>🔐 ${steps.length + 1}. Requests sensitive information</strong>
                <p>
                    The attacker may attempt to obtain an OTP,
                    PIN, password, CVV, or other sensitive information.
                </p>
            </div>
        `);
    }


    // IF NO STEPS
    if (steps.length === 0) {

        return `
            <p>
                ✓ No clear multi-step scam pattern was detected.
            </p>
        `;
    }


    // ADD ARROWS BETWEEN STEPS

    let reconstruction = "";

    steps.forEach((step, index) => {

        reconstruction += step;

        if (index < steps.length - 1) {

            reconstruction += `
                <div style="
                    text-align:center;
                    font-size:25px;
                    margin:5px 0;
                ">
                    ↓
                </div>
            `;
        }
    });


    return reconstruction;
}

// =====================================================
// DISPLAY SCAM ANALYSIS
// =====================================================

function displayScamAnalysis(result, container) {

    container.innerHTML = `

        <h2>🛡️ ScamShield Analysis</h2>


        <div class="risk-score">

            <h3>Risk Score</h3>

            <div class="score">
                ${result.riskScore}/100
            </div>

            <div class="risk-bar">

                <div
                    class="risk-progress"
                    style="width: ${result.riskScore}%">
                </div>

            </div>

            <h3>
                ${result.riskLevel} RISK
            </h3>

        </div>


        <h3>🎯 Scam Type</h3>

        <p>
            ${result.scamType}
        </p>


        <h3>🧠 Social Engineering Tactics</h3>

        <div class="tactics">

            ${
                result.tactics.length > 0

                ? result.tactics.map(tactic => {

                    return `

                        <div class="tactic-box">

                            <strong>
                                ${tactic}
                            </strong>

                            <p>
                                ${getTacticExplanation(tactic)}
                            </p>

                        </div>

                    `;

                }).join("")

                : "<p>✓ No major manipulation tactics detected.</p>"
            }

        </div>


        <h3>⚠️ Detected Indicators</h3>

        <ul>

            ${
                result.indicators.length > 0

                ? result.indicators
                    .map(item =>
                        `<li>⚠️ ${item}</li>`
                    )
                    .join("")

                : "<li>✓ No major indicators detected</li>"
            }

        </ul>
         <h3>🧩 Scam Attack Reconstruction</h3>

<div class="tactics">

    ${generateAttackReconstruction(result)}

</div>

        <h3>🛡️ Recommended Action</h3>

        <p>
            ${result.recommendedAction}
        </p>

    `;
}


// =====================================================
// 1. MESSAGE ANALYZER
// =====================================================

async function analyzeMessage() {

    let message =
        document.getElementById("message").value;

    if (message.trim() === "") {
        alert("Please enter a message to analyze.");
        return;
    }

    let resultBox =
        document.getElementById("result");

    resultBox.style.display = "block";

    resultBox.innerHTML = `
        <h2>🛡️ ScamShield Analysis</h2>
        <p>🔄 Analyzing message...</p>
    `;

    try {

        // Send message to backend
        const response =
            await fetch(
                "http://localhost:8080/api/analyze",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "text/plain"
                    },
                    body: message
                }
            );

        if (!response.ok) {
            throw new Error("Backend returned an error.");
        }

        const result =
            await response.json();

        // Display normal ScamShield analysis
        displayScamAnalysis(
            result,
            resultBox
        );

        // Add Demo AI button
        resultBox.innerHTML += `
            <div style="margin-top: 20px;">
                <button onclick="runDemoAI()">
                    🤖 Get AI Explanation
                </button>
            </div>

            <div id="demoAIResult"
                 class="tactic-box"
                 style="margin-top: 15px; display: none;">
            </div>
        `;

    } catch (error) {

        console.error(error);

        resultBox.innerHTML = `
            <h2>❌ Connection Error</h2>
            <p>
                Could not connect to the ScamShield backend.
            </p>
            <p>
                Make sure the Spring Boot backend is
                running on port 8080.
            </p>
        `;
    }
}
async function runDemoAI() {

    let message =
        document.getElementById("message").value;

    let aiResult =
        document.getElementById("demoAIResult");

    aiResult.style.display = "block";

    aiResult.innerHTML = `
        <h3>🤖 ScamShield Demo AI</h3>
        <p>🔄 Generating AI explanation...</p>
    `;

    try {

        const response =
            await fetch(
                "http://localhost:8080/api/ai-demo",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "text/plain"
                    },
                    body: message
                }
            );

        if (!response.ok) {
            throw new Error("Demo AI returned an error.");
        }

        const result =
            await response.text();

        aiResult.innerHTML = `
            <h3>🤖 ScamShield Demo AI</h3>
            <p>
                ${result.replace(/\n/g, "<br>")}
            </p>
        `;

    } catch (error) {

        console.error(error);

        aiResult.innerHTML = `
            <h3>❌ Demo AI Error</h3>
            <p>
                Could not connect to Demo AI.
            </p>
        `;
    }
}
// =====================================================
// 2. URL ANALYZER
// =====================================================

async function checkURL() {

    let url =
        document.getElementById("urlInput").value;

    if (url.trim() === "") {

        alert("Please enter a URL to analyze.");

        return;
    }

    let resultBox =
        document.getElementById("urlResult");

    resultBox.style.display = "block";

    resultBox.innerHTML = `

        <h2>🛡️ URL Analysis</h2>

        <p>
            🔄 Analyzing URL...
        </p>

    `;

    try {

        const response =
            await fetch(
                "http://localhost:8080/api/analyze-url",
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "text/plain"
                    },

                    body: url
                }
            );


        if (!response.ok) {

            throw new Error(
                "Backend returned an error."
            );

        }


        const result =
            await response.json();


        let indicatorsHTML = "";

        if (result.indicators &&
            result.indicators.length > 0) {

            indicatorsHTML =
                result.indicators
                    .map(item => `<li>${item}</li>`)
                    .join("");

        }
        else {

            indicatorsHTML =
                "<li>No major suspicious indicators detected.</li>";
        }


        resultBox.innerHTML = `

            <h2>🛡️ URL Analysis</h2>

            <h3>
                Risk Score: ${result.riskScore}/100
            </h3>

            <p>
                <strong>Risk Level:</strong>
                ${result.riskLevel}
            </p>

            <h3>🔎 Detected Indicators</h3>

            <ul>
                ${indicatorsHTML}
            </ul>

            <h3>💡 Recommended Action</h3>

            <p>
                ${result.recommendedAction}
            </p>

        `;

    }

    catch (error) {

        console.error(error);

        resultBox.innerHTML = `

            <h2>❌ Connection Error</h2>

            <p>
                Could not connect to the ScamShield backend.
            </p>

            <p>
                Make sure the Spring Boot backend
                is running on port 8080.
            </p>

        `;

    }
}


// =====================================================
// 3. SCREENSHOT + OCR
// =====================================================
async function analyzeScreenshot() {

    let file =
        document.getElementById("screenshotInput").files[0];


    if (!file) {

        alert("Please upload a screenshot.");

        return;
    }


    let screenshotResult =
        document.getElementById("screenshotResult");


    screenshotResult.style.display = "block";


    screenshotResult.innerHTML = `

        <h2>📸 Screenshot Analysis</h2>

        <p>
            🔄 Reading text from the screenshot...
        </p>

    `;


    try {

        // =========================================
        // STEP 1: OCR
        // =========================================

        const result =
            await Tesseract.recognize(

                file,

                "eng",

                {

                    logger: function(info) {

                        if (
                            info.status ===
                            "recognizing text"
                        ) {

                            let progress =
                                Math.round(
                                    info.progress * 100
                                );


                            screenshotResult.innerHTML = `

                                <h2>
                                    📸 Screenshot Analysis
                                </h2>

                                <p>
                                    🔄 Reading screenshot...
                                    ${progress}%
                                </p>

                            `;
                        }

                    }

                }

            );


        let extractedText =
            result.data.text.trim();


        // =========================================
        // STEP 2: CHECK OCR RESULT
        // =========================================

        if (extractedText === "") {

            screenshotResult.innerHTML = `

                <h2>
                    📸 Screenshot Analysis
                </h2>

                <p>
                    ⚠️ No readable text was detected.
                </p>

                <p>
                    Try uploading a clearer screenshot.
                </p>

            `;

            return;
        }


        // =========================================
        // STEP 3: SEND TEXT TO BACKEND
        // =========================================

        screenshotResult.innerHTML = `

            <h2>
                📸 Screenshot Analysis
            </h2>

            <h3>📝 Extracted Text</h3>

            <div class="tactic-box">

                <p>
                    ${extractedText.replace(
                        /\n/g,
                        "<br>"
                    )}
                </p>

            </div>

            <p>
                🔄 Sending extracted text to ScamShield backend...
            </p>

        `;


        const response =
            await fetch(
                "http://localhost:8080/api/analyze-screenshot",
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "text/plain"
                    },

                    body: extractedText
                }
            );


        if (!response.ok) {

            throw new Error(
                "Backend returned an error."
            );

        }


        // =========================================
        // STEP 4: GET BACKEND RESULT
        // =========================================

        const scamResult =
            await response.json();


        // =========================================
        // STEP 5: DISPLAY RESULT
        // =========================================

        screenshotResult.innerHTML = `

            <h2>
                📸 Screenshot Analysis
            </h2>

            <h3>📝 Extracted Text</h3>

            <div class="tactic-box">

                <p>
                    ${extractedText.replace(
                        /\n/g,
                        "<br>"
                    )}
                </p>

            </div>

        `;


        displayScamAnalysis(
            scamResult,
            screenshotResult
        );

    }


    catch (error) {

        console.error(error);


        screenshotResult.innerHTML = `

            <h2>
                📸 Screenshot Analysis
            </h2>

            <p>
                ❌ Unable to connect to the ScamShield backend.
            </p>

            <p>
                Make sure the Spring Boot backend
                is running on port 8080.
            </p>

        `;
    }
}


// =====================================================
// 4. QR CODE ANALYZER
// =====================================================

async function analyzeQR() {

    let file =
        document.getElementById("qrInput").files[0];

    if (!file) {
        alert("Please upload a QR code image.");
        return;
    }

    let qrResult =
        document.getElementById("qrResult");

    qrResult.style.display = "block";

    qrResult.innerHTML = `
        <h2>📱 QR Code Analysis</h2>
        <p>🔄 Reading QR code...</p>
    `;

    try {

        // STEP 1: READ QR IMAGE
        const image =
            await createImageBitmap(file);

        const canvas =
            document.createElement("canvas");

        canvas.width = image.width;
        canvas.height = image.height;

        const context =
            canvas.getContext("2d");

        context.drawImage(
            image,
            0,
            0
        );

        // STEP 2: DECODE QR
        const imageData =
            context.getImageData(
                0,
                0,
                canvas.width,
                canvas.height
            );

        const code =
            jsQR(
                imageData.data,
                imageData.width,
                imageData.height
            );

        if (!code) {

            qrResult.innerHTML = `
                <h2>📱 QR Code Analysis</h2>
                <p>⚠️ No readable QR code was detected.</p>
                <p>Try uploading a clearer QR image.</p>
            `;

            return;
        }

        let qrData = code.data;

        // STEP 3: SHOW DECODED CONTENT
        qrResult.innerHTML = `
            <h2>📱 QR Code Analysis</h2>

            <h3>🔗 QR Content</h3>

            <div class="tactic-box">
                <p>${qrData}</p>
            </div>

            <p>
                🔄 Sending QR content to ScamShield backend...
            </p>
        `;

        // STEP 4: SEND TO BACKEND
        const response =
            await fetch(
                "http://localhost:8080/api/analyze-qr",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "text/plain"
                    },
                    body: qrData
                }
            );

        if (!response.ok) {
            throw new Error(
                "Backend returned an error."
            );
        }

        // STEP 5: GET BACKEND RESULT
        const result =
            await response.json();

        // STEP 6: DISPLAY RESULT
        let indicatorsHTML = "";

        if (
            result.indicators &&
            result.indicators.length > 0
        ) {

            indicatorsHTML =
                result.indicators
                    .map(item => `<li>${item}</li>`)
                    .join("");

        } else {

            indicatorsHTML =
                "<li>No major suspicious indicators detected.</li>";
        }

        qrResult.innerHTML = `
            <h2>📱 QR Code Analysis</h2>

            <h3>🔗 QR Content</h3>

            <div class="tactic-box">
                <p>${qrData}</p>
            </div>

            <h3>🛡️ Risk Score: ${result.riskScore}/100</h3>

            <p>
                <strong>Risk Level:</strong>
                ${result.riskLevel}
            </p>

            <h3>🔎 Detected Indicators</h3>

            <ul>
                ${indicatorsHTML}
            </ul>

            <h3>💡 Recommended Action</h3>

            <p>
                ${result.recommendedAction}
            </p>
        `;

    }

    catch (error) {

        console.error(error);

        qrResult.innerHTML = `
            <h2>📱 QR Code Analysis</h2>

            <p>
                ❌ Unable to connect to the
                ScamShield backend.
            </p>

            <p>
                Make sure the Spring Boot backend
                is running on port 8080.
            </p>
        `;
    }
}


// =====================================================
// QR URL ANALYSIS
// =====================================================

function analyzeQRURL(url, container) {

    let riskScore = 0;

    let indicators = [];

    let text =
        url.toLowerCase();


    // HTTPS

    if (!text.startsWith("https://")) {

        riskScore += 20;

        indicators.push(
            "Website does not use HTTPS"
        );
    }


    // URL LENGTH

    if (url.length > 75) {

        riskScore += 15;

        indicators.push(
            "Unusually long URL"
        );
    }


    // SECURITY WORDS

    if (
        text.includes("verify") ||
        text.includes("login") ||
        text.includes("account") ||
        text.includes("secure") ||
        text.includes("update")
    ) {

        riskScore += 15;

        indicators.push(
            "Suspicious security-related words detected"
        );
    }


    // PAYMENT

    if (
        text.includes("upi") ||
        text.includes("pay") ||
        text.includes("payment")
    ) {

        riskScore += 20;

        indicators.push(
            "Payment-related QR content detected"
        );
    }


    // @ SYMBOL

    if (text.includes("@")) {

        riskScore += 20;

        indicators.push(
            "URL contains '@' symbol"
        );
    }


    if (riskScore > 100) {

        riskScore = 100;
    }


    let riskLevel;


    if (riskScore >= 81) {

        riskLevel = "CRITICAL";
    }

    else if (riskScore >= 61) {

        riskLevel = "HIGH";
    }

    else if (riskScore >= 31) {

        riskLevel = "MEDIUM";
    }

    else {

        riskLevel = "LOW";
    }


    container.innerHTML = `

        <h2>
            📱 QR Code Analysis
        </h2>


        <h3>
            🔗 Decoded URL
        </h3>


        <div class="tactic-box">

            <p>
                ${url}
            </p>

        </div>


        <h3>
            Risk Score
        </h3>


        <div class="score">
            ${riskScore}/100
        </div>


        <div class="risk-bar">

            <div
                class="risk-progress"
                style="width: ${riskScore}%">
            </div>

        </div>


        <h3>
            ${riskLevel} RISK
        </h3>


        <h3>
            ⚠️ Detected Indicators
        </h3>


        <ul>

            ${
                indicators.length > 0

                ? indicators
                    .map(item =>
                        `<li>⚠️ ${item}</li>`
                    )
                    .join("")

                : "<li>✓ No major QR indicators detected</li>"
            }

        </ul>


        <h3>
            🛡️ Recommended Action
        </h3>


        <p>

            ${
                riskScore >= 61

                ? "Do not open the decoded link or make a payment. Verify the destination through an official source."

                : "Check the decoded information carefully before opening the link or making a payment."
            }

        </p>

    `;
}