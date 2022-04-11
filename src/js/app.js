const fs = require("fs");
const Path = require("./path");

const main = () => {
    try {
        const pathList = Path.list("Loan");

        for (const path of pathList) {
            console.log(path.toString());

            const content = fs.readFileSync(path.model, "utf8");
            const formatter = content.replaceAll("NotificationCenter", "Loan");

            /* if (!fs.existsSync(path.destination)) {
                fs.mkdirSync(path.destination, {recursive:true});
            }
        
            fs.writeFileSync(path.destination, formatter); */
        }
    } catch(error) {
        console.error("Problems generating the files. ", error);
    }
}

main();