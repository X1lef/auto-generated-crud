class Path {
    constructor(model, destination, fileNameDestination) {
        this.model = model;
        this.destination = destination;
        this.fileNameDestination = fileNameDestination;
    }

    static directoryDestination() {
        const directoryDAO = process.cwd();
        //Se elimina de la ruta la carpeta /beans
        return directoryDAO.substring(0, directoryDAO.length - 3);
    }

    static directoryModel() {
        const directoryJs = __dirname;
        //Se elimina de la ruta la carpeta /js
        const directorySrc = directoryJs.substring(0, directoryJs.length - 3);
        return `${directorySrc}/assets/baseFile`;
    }

    get fileDestination() {
        return `${this.destination}${this.fileNameDestination}`;
    }

    static list(entityName) {
        const directoryDestination = Path.directoryDestination();
        const directoryModel = Path.directoryModel();

        return [
            new Path(`${directoryModel}/Controller.java`, `${directoryDestination}/controllers/`, `${entityName}API.java`),
            new Path(`${directoryModel}/ServiceImpl.java`, `${directoryDestination}/services/`, `${entityName}ServiceImpl.java`),
            new Path(`${directoryModel}/DAOImpl.java`, `${directoryDestination}/dao/`, `${entityName}DAOImpl.java`),
            new Path(`${directoryModel}/TBL.java`, `${directoryDestination}/dao/tables/`, `${entityName}TBL.java`),

            //Beans
            new Path(`${directoryModel}/ResultWithPaginationDTO.java`, `${directoryDestination}/beans/`, `${entityName}ResultWithPagination.java`),
            new Path(`${directoryModel}/ResultDTO.java`, `${directoryDestination}/beans/`, `${entityName}Result.java`),

            //Interface
            new Path(`${directoryModel}/DAOInterface.java`, `${directoryDestination}/dao/`, `${entityName}DAO.java`),
            new Path(`${directoryModel}/ServiceInterface.java`, `${directoryDestination}/services/`, `${entityName}Service.java`),
        ];
    }

    toString() {
        return `Model: ${this.model}, Destination: ${this.fileDestination}`;
    }
}

module.exports = Path;