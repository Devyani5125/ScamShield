package com.scamshield.backend;

import org.springframework.stereotype.Service;

@Service
public class DemoAIService {

    public String analyzeWithDemoAI(String message) {

        String text = message.toLowerCase();

        StringBuilder analysis = new StringBuilder();

        analysis.append("🤖 ScamShield AI Scam Analysis\n\n");

        // Scam Type
        if (text.contains("bank") ||
            text.contains("kyc") ||
            text.contains("otp") ||
            text.contains("account")) {

            analysis.append("🎯 Scam Type: Bank / KYC Phishing\n\n");

        } else if (text.contains("job") ||
                   text.contains("salary") ||
                   text.contains("hiring") ||
                   text.contains("work from home")) {

            analysis.append("🎯 Scam Type: Possible Fake Job Scam\n\n");

        } else if (text.contains("prize") ||
                   text.contains("lottery") ||
                   text.contains("reward") ||
                   text.contains("won")) {

            analysis.append("🎯 Scam Type: Prize / Lottery Scam\n\n");

        } else if (text.contains("investment") ||
                   text.contains("profit") ||
                   text.contains("crypto") ||
                   text.contains("trading")) {

            analysis.append("🎯 Scam Type: Possible Investment Scam\n\n");

        } else {

            analysis.append("🎯 Scam Type: Suspicious Message\n\n");
        }


        // Social Engineering Tactics

        analysis.append("🧠 Social Engineering Tactics:\n");

        boolean tacticDetected = false;

        if (text.contains("urgent") ||
            text.contains("immediately") ||
            text.contains("hurry") ||
            text.contains("within 24 hours") ||
            text.contains("act fast")) {

            analysis.append("• ⏰ Urgency — tries to make the user act quickly.\n");
            tacticDetected = true;
        }

        if (text.contains("blocked") ||
            text.contains("suspended") ||
            text.contains("warning") ||
            text.contains("penalty") ||
            text.contains("police") ||
            text.contains("arrest")) {

            analysis.append("• 😨 Fear / Threat — creates fear of a negative consequence.\n");
            tacticDetected = true;
        }

        if (text.contains("bank") ||
            text.contains("government") ||
            text.contains("official") ||
            text.contains("customer care") ||
            text.contains("support team")) {

            analysis.append("• 🏦 Authority / Impersonation — appears to represent a trusted organization.\n");
            tacticDetected = true;
        }

        if (text.contains("prize") ||
            text.contains("reward") ||
            text.contains("cashback") ||
            text.contains("lottery") ||
            text.contains("free money")) {

            analysis.append("• 🎁 Reward / Greed — uses an attractive financial reward.\n");
            tacticDetected = true;
        }

        if (text.contains("otp") ||
            text.contains("password") ||
            text.contains("pin") ||
            text.contains("cvv") ||
            text.contains("card number") ||
            text.contains("bank details")) {

            analysis.append("• 🔐 Sensitive Information Request — attempts to obtain confidential information.\n");
            tacticDetected = true;
        }

        if (text.contains("click") ||
            text.contains("link") ||
            text.contains("verify") ||
            text.contains("login") ||
            text.contains("download")) {

            analysis.append("• 🔗 Suspicious Action — encourages clicking, logging in, verifying, or downloading.\n");
            tacticDetected = true;
        }

        if (!tacticDetected) {

            analysis.append("• No major social-engineering tactic detected.\n");
        }

        analysis.append("\n");


        // Why suspicious

        analysis.append("🔍 Why This Message May Be Suspicious:\n");

        boolean reasonDetected = false;

        if (text.contains("urgent") ||
            text.contains("immediately") ||
            text.contains("within 24 hours")) {

            analysis.append("• It creates pressure to act quickly.\n");
            reasonDetected = true;
        }

        if (text.contains("blocked") ||
            text.contains("suspended") ||
            text.contains("penalty")) {

            analysis.append("• It uses a possible negative consequence to create fear.\n");
            reasonDetected = true;
        }

        if (text.contains("otp") ||
            text.contains("password") ||
            text.contains("pin") ||
            text.contains("cvv")) {

            analysis.append("• It requests sensitive information that should be protected.\n");
            reasonDetected = true;
        }

        if (text.contains("click") ||
            text.contains("verify") ||
            text.contains("login") ||
            text.contains("link")) {

            analysis.append("• It encourages the user to take an action that may expose information.\n");
            reasonDetected = true;
        }

        if (!reasonDetected) {

            analysis.append("• No strong suspicious pattern was identified by the demo rules.\n");
        }

        analysis.append("\n");


        // Possible attacker goal

        analysis.append("🎯 Possible Attacker Goal:\n");

        if (text.contains("otp") ||
            text.contains("password") ||
            text.contains("pin") ||
            text.contains("cvv") ||
            text.contains("card number")) {

            analysis.append("The attacker may be attempting to obtain sensitive credentials or financial information.\n\n");

        } else if (text.contains("click") ||
                   text.contains("link") ||
                   text.contains("login") ||
                   text.contains("verify")) {

            analysis.append("The attacker may be attempting to redirect the user to a suspicious website or login page.\n\n");

        } else if (text.contains("money") ||
                   text.contains("payment") ||
                   text.contains("reward") ||
                   text.contains("prize")) {

            analysis.append("The attacker may be attempting to obtain money or financial information.\n\n");

        } else {

            analysis.append("The exact attacker goal could not be determined from the available message.\n\n");
        }


        // Recommended action

        analysis.append("🛡️ Safe Action:\n");

        analysis.append(
            "Do not share OTPs, passwords, PINs, CVV, or other confidential information. " +
            "Avoid suspicious links and verify unexpected requests through the organization's official website, app, or customer-support channel.\n\n"
        );


        // Disclaimer

        analysis.append(
            "⚠️ Demo Mode: This explanation is generated locally by ScamShield using predefined detection rules. " +
            "It does not use the OpenAI API and should be treated as a risk assessment, not a certainty."
        );

        return analysis.toString();
    }
}