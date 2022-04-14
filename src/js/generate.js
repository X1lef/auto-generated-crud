const fs = require("fs");
const Path = require("./path");

function generateCRUD(entityName) {
    try {
        const pathList = Path.list(entityName);
        let content, formatter;

        for (const path of pathList) {
            console.log(path.toString());

            content = fs.readFileSync(path.model, "utf8");
            content = replace(content, entityName);

            if (!fs.existsSync(path.destination)) {
                fs.mkdirSync(path.destination, {recursive:true});
            }
        
            fs.writeFileSync(path.fileDestination, formatter);
        }
    } catch(error) {
        console.error("Problems generating the files. ", error);
    }
}

function replace(content, entityName) {
    //TODO: Obtener paquete actual.
    
    let formatter = content.replaceAll("ClassModel", entityName);
    return formatter;
}

module.exports = {
    generateCRUD
}