package com.scamshield.backend;

import org.springframework.stereotype.Service;

@Service
public class ScamAnalyzer {

    public ScamAnalysis analyze(String message) {

        ScamAnalysis result = new ScamAnalysis();

        String text = message.toLowerCase();

        int score = 0;

        // =========================
        // 1. URGENCY
        // =========================
        if (text.contains("urgent") ||
            text.contains("immediately") ||
            text.contains("hurry") ||
            text.contains("act now") ||
            text.contains("act fast") ||
            text.contains("within 24 hours") ||
            text.contains("today") ||
            text.contains("last chance") ||
            text.contains("limited time")) {

            score += 15;

            result.getIndicators().add("Urgency detected");
            result.getTactics().add("⏰ Urgency");
        }


        // =========================
        // 2. FEAR / THREAT
        // =========================
        if (text.contains("blocked") ||
            text.contains("suspended") ||
            text.contains("deactivated") ||
            text.contains("closed") ||
            text.contains("warning") ||
            text.contains("penalty") ||
            text.contains("legal action") ||
            text.contains("police") ||
            text.contains("arrest") ||
            text.contains("freeze") ||
            text.contains("restriction")) {

            score += 20;

            result.getIndicators().add(
                    "Threat or fear-based language detected");

            result.getTactics().add("😨 Fear / Threat");
        }


        // =========================
        // 3. SENSITIVE INFORMATION
        // =========================
        if (text.contains("otp") ||
            text.contains("password") ||
            text.contains("pin") ||
            text.contains("cvv") ||
            text.contains("card number") ||
            text.contains("bank details") ||
            text.contains("login details") ||
            text.contains("credentials")) {

            score += 25;

            result.getIndicators().add(
                    "Sensitive information request detected");

            result.getTactics().add(
                    "🔐 Sensitive Information Request");
        }


        // =========================
        // 4. SUSPICIOUS ACTION
        // =========================
        if (text.contains("click") ||
            text.contains("click here") ||
            text.contains("link") ||
            text.contains("verify") ||
            text.contains("verification") ||
            text.contains("open") ||
            text.contains("download") ||
            text.contains("login") ||
            text.contains("sign in") ||
            text.contains("apply now") ||
            text.contains("apply here") ||
            text.contains("claim now") ||
            text.contains("claim here") ||
            text.contains("register now")) {

            score += 15;

            result.getIndicators().add(
                    "Suspicious action or link detected");

            result.getTactics().add(
                    "🔗 Suspicious Link / Action");
        }


        // =========================
        // 5. MONEY / REWARD
        // =========================
        if (text.contains("₹") ||
            text.contains("rs.") ||
            text.contains("money") ||
            text.contains("payment") ||
            text.contains("prize") ||
            text.contains("won") ||
            text.contains("cashback") ||
            text.contains("reward") ||
            text.contains("lottery") ||
            text.contains("free money") ||
            text.contains("cash")) {

            score += 20;

            result.getIndicators().add(
                    "Financial or reward-related content detected");

            result.getTactics().add("🎁 Reward / Greed");
        }


        // =========================
        // 6. BANK / KYC / ACCOUNT
        // =========================
        if (text.contains("bank") ||
            text.contains("account") ||
            text.contains("kyc") ||
            text.contains("net banking") ||
            text.contains("credit card") ||
            text.contains("debit card") ||
            text.contains("upi") ||
            text.contains("wallet")) {

            score += 10;

            result.getIndicators().add(
                    "Possible financial-service impersonation");

            result.getTactics().add(
                    "🏦 Authority / Impersonation");
        }


        // =========================
        // 7. LOAN SCAM
        // =========================
        if (text.contains("loan") ||
            text.contains("personal loan") ||
            text.contains("instant loan") ||
            text.contains("quick loan") ||
            text.contains("loan approved") ||
            text.contains("loan offer") ||
            text.contains("loan application")) {

            score += 15;

            result.getIndicators().add(
                    "Possible suspicious loan or financial offer");

            result.getTactics().add(
                    "💳 Suspicious Loan Offer");
        }


        // =========================
        // 8. LARGE / EASY MONEY PROMISE
        // =========================
        if (text.contains("instant") ||
            text.contains("guaranteed") ||
            text.contains("easy money") ||
            text.contains("quick money") ||
            text.contains("earn") ||
            text.contains("guaranteed profit") ||
            text.contains("guaranteed return") ||
            text.contains("double your money") ||
            text.contains("get rich")) {

            score += 15;

            result.getIndicators().add(
                    "Unusually attractive financial promise detected");

            result.getTactics().add(
                    "💰 Unrealistic Financial Promise");
        }


        // =========================
        // 9. FAKE JOB SCAM
        // =========================
        if (text.contains("job") ||
            text.contains("salary") ||
            text.contains("hiring") ||
            text.contains("work from home") ||
            text.contains("part time") ||
            text.contains("earn money") ||
            text.contains("registration fee") ||
            text.contains("joining fee")) {

            score += 20;

            result.getIndicators().add(
                    "Possible fake job or employment scam");

            result.getTactics().add(
                    "💼 Fake Job / Employment");
        }


        // =========================
        // 10. INVESTMENT SCAM
        // =========================
        if (text.contains("investment") ||
            text.contains("profit") ||
            text.contains("crypto") ||
            text.contains("trading") ||
            text.contains("guaranteed return") ||
            text.contains("double your money")) {

            score += 20;

            result.getIndicators().add(
                    "Possible suspicious investment opportunity");

            result.getTactics().add(
                    "📈 Investment / Profit Promise");
        }


        // =========================
        // 11. IMPERSONATION
        // =========================
        if (text.contains("official") ||
            text.contains("customer care") ||
            text.contains("support team") ||
            text.contains("government") ||
            text.contains("income tax") ||
            text.contains("police department") ||
            text.contains("customer support")) {

            score += 15;

            result.getIndicators().add(
                    "Possible authority or organization impersonation");

            result.getTactics().add(
                    "👤 Impersonation");
        }


        // =========================
        // 12. APPLICATION / CLAIM PRESSURE
        // =========================
        if (text.contains("apply here") ||
            text.contains("apply now") ||
            text.contains("claim here") ||
            text.contains("claim now") ||
            text.contains("register now") ||
            text.contains("complete your application")) {

            score += 10;

            result.getIndicators().add(
                    "Call-to-action pressure detected");

            result.getTactics().add(
                    "🎯 Call-to-Action Pressure");
        }
        // =========================
// 12. FAKE CUSTOMER SUPPORT
// =========================
// =========================
// 12. FAKE CUSTOMER SUPPORT
// =========================
if (text.contains("helpline") ||
    text.contains("toll free") ||
    text.contains("call us") ||
    text.contains("contact support")) {

    score += 15;

    result.getIndicators().add(
            "Possible fake customer support or helpline");

    result.getTactics().add(
            "📞 Fake Customer Support");
}


// =========================
// 13. GOVERNMENT IMPERSONATION
// =========================
if (text.contains("government") ||
    text.contains("income tax") ||
    text.contains("tax department") ||
    text.contains("police department") ||
    text.contains("government notice") ||
    text.contains("government officer") ||
    text.contains("official notice")) {

    score += 15;

    result.getIndicators().add(
            "Possible government or official impersonation");

    result.getTactics().add(
            "🏛️ Government Impersonation");
}

        // =========================
        // LIMIT SCORE
        // =========================
        if (score > 100) {
            score = 100;
        }


        // =========================
        // RISK LEVEL
        // =========================
        String riskLevel;

        if (score >= 81) {
            riskLevel = "CRITICAL";
        }
        else if (score >= 61) {
            riskLevel = "HIGH";
        }
        else if (score >= 31) {
            riskLevel = "MEDIUM";
        }
        else {
            riskLevel = "LOW";
        }


        // =========================
        // SCAM TYPE
        // =========================
        String scamType = "Suspicious Message";

        if (text.contains("loan") ||
            text.contains("instant loan") ||
            text.contains("loan offer")) {

            scamType = "Loan / Financial Scam";
        }
        else if (text.contains("job") ||
                 text.contains("salary") ||
                 text.contains("hiring") ||
                 text.contains("work from home")) {

            scamType = "Fake Job Scam";
        }
        else if (text.contains("prize") ||
                 text.contains("lottery") ||
                 text.contains("won") ||
                 text.contains("reward")) {

            scamType = "Prize / Lottery Scam";
        }
        else if (text.contains("investment") ||
                 text.contains("profit") ||
                 text.contains("crypto") ||
                 text.contains("trading")) {

            scamType = "Investment Scam";
        }
        else if (text.contains("bank") ||
                 text.contains("kyc") ||
                 text.contains("account") ||
                 text.contains("otp")) {

            scamType = "Bank / KYC Phishing";
        }
        if (text.contains("loan") ||
    text.contains("personal loan") ||
    text.contains("instant loan") ||
    text.contains("quick loan") ||
    text.contains("loan approved")) {

    result.setScamType("Loan / Financial Scam");

} else if (text.contains("job") ||
           text.contains("salary") ||
           text.contains("hiring") ||
           text.contains("work from home")) {

    result.setScamType("Fake Job Scam");

} else if (text.contains("prize") ||
           text.contains("lottery") ||
           text.contains("won") ||
           text.contains("reward")) {

    result.setScamType("Prize / Lottery Scam");

} else if (text.contains("investment") ||
           text.contains("profit") ||
           text.contains("crypto") ||
           text.contains("trading")) {

    result.setScamType("Investment Scam");

} else if (text.contains("government") ||
           text.contains("income tax") ||
           text.contains("tax department") ||
           text.contains("police department") ||
           text.contains("government notice") ||
           text.contains("government officer") ||
           text.contains("official notice")) {

    result.setScamType("Government Impersonation");

} else if (text.contains("bank") ||
           text.contains("account") ||
           text.contains("kyc") ||
           text.contains("otp") ||
           text.contains("net banking") ||
           text.contains("credit card") ||
           text.contains("debit card") ||
           text.contains("upi")) {

    result.setScamType("Bank/KYC Phishing");

} else {
    result.setScamType("Suspicious Message");
}

        // =========================
        // RECOMMENDED ACTION
        // =========================
        String action;

        if (score >= 61) {

            action =
                    "Do not click suspicious links or share passwords, " +
                    "OTPs, PINs, CVV, or financial information. " +
                    "Verify the request through the organization's official " +
                    "website, app, or customer-support channel.";
        }
        else if (score >= 31) {

            action =
                    "Be cautious. Do not provide sensitive information " +
                    "or make payments. Verify the sender, organization, " +
                    "or offer through an official source.";
        }
        else {

            action =
                    "No major scam indicators detected. Still verify " +
                    "unexpected messages before taking action.";
        }


        // =========================
        // SET RESULT
        // =========================
        result.setRiskScore(score);
        result.setRiskLevel(riskLevel);
        result.setScamType(scamType);
        result.setRecommendedAction(action);

        return result;
    }
}