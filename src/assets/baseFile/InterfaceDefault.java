public interface InterfaceDefault<E,P,R> {
    P find(E classModel) throws APIException;
    R findById(Long iid) throws APIException;
    R create(E classModel) throws APIException;
    R update(E classModel) throws APIException;
    R delete(Long iid) throws APIException;
}
