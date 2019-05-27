
package data;

import domain.Point;
import domain.Figure;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class XMLFigureManager {
    
    //variables
    private Document document;
    private Element root; //raíz
    private String path; //camino, ruta

    //start constructor
    public XMLFigureManager(String path) throws IOException, JDOMException {
        //ruta en la que se encuentra el archivo XML
        this.path = path;
        
        File fileFigure = new File(path);//esto es solo para hacer validación
        if(fileFigure.exists()){
            //1. EL ARCHIVO YA EXISTE, ENTONCES LO CARGO EN MEMORIA
            
            //toma la estructura de datos y las carga en memoria
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true);
            
            //carga la memoria
            this.document = saxBuilder.build(path);
            this.root = this.document.getRootElement();
        }
        else{
            //2. EL ARCHIVO NO EXISTE, ENTONCES LO CREO y LUEGO LO CARGO EN MEMORIA   
            //CREAMOS EL ELEMENTO RAIZ
            this.root = new Element("figure");
            
            //CREAMOS EL DOCUMENTO
            this.document = new Document(this.root);
            
            //GUARDAMOS EN DISCO DURO
            storeXML();     //almacenar
        }
    }//end constructor
    
    //almacena en disco duro nuestro documento xml en la ruta especificada
    private void storeXML() throws IOException{
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(document, new PrintWriter(path));
    }
    
    //metodo para insertar un nuevo estudiante en el documento xml
    public void insertFigure(Figure square) throws IOException{
        //INSERTAMOS EN EL DOCUMENTO EN MEMORIA
        //para insertar en xml, primero se crean los elementos
        
        //crear el estudiante
        Element eFigure = new Element("figure");
        //agregamos atributo
        eFigure.setAttribute("identification", square.getIdentification());
        
        //crear el elemento nombre
        Element ePointPosition = new Element("pointposition");
        Element x = new Element("x");
        x.addContent(String.valueOf(square.getPointPosition().getX()));
        
        Element y = new Element("y");
        y.addContent(String.valueOf(square.getPointPosition().getY()));
        
        ePointPosition.addContent(x);
        ePointPosition.addContent(y);
        
        //creamos el elemento nota
        Element eSizeX = new Element("sizex");
        eSizeX.addContent(String.valueOf(square.getWidth()));
        
        Element eSizeY = new Element("sizey");
        eSizeY.addContent(String.valueOf(square.getLength()));
        
        //agregar al elemento student el contenido de nombre y nota
        eFigure.addContent(eSizeX);
        eFigure.addContent(eSizeY);
        eFigure.addContent(ePointPosition);
        
        //AGREGAMOS AL ROOT
        this.root.addContent(eFigure);
        
        //FINALMENTE: GUARDAR EN DD
        storeXML();
    }//end insert
    
    
    //delete
    public void deleteFigure() throws IOException{
        List elementList = this.root.getChildren();
        elementList.remove(1); //Esta removiento el estudiante en la posición que se le esta dando
        
        //FINALMENTE: GUARDAR EN Disco Duro
        storeXML();
    }
    
    //metodo para obtener todos los estudiantes en un arreglo
    public Figure[] getAllFigures(){
        //obtenemos la cantidad de estudiantes
        int FiguresQuantity = this.root.getContentSize();
        
        //obtenemos una lista con todos los elementos de root
        List elementList = this.root.getChildren();
        
        //definimos el tamanno del arreglo
        Figure[] FiguresArray = new Figure[FiguresQuantity];
        
        //recorremos la lista para ir creando los objetos de tipo estudiante
        int count = 0;
        for(Object currentObject: elementList){
            //transformo el object
            Element currentElement = (Element)currentObject;
            
            //crear el objeto Square
            Figure figure = new Figure();
            
            //establezco el id
            figure.setIdentification(currentElement.getAttributeValue("identification"));
            
            int sqPointX = Integer.parseInt(currentElement.getChild("pointposition").getChild("x").getValue());
            int sqPointY = Integer.parseInt(currentElement.getChild("pointposition").getChild("y").getValue());
            Point sqPoint = new Point(sqPointX, sqPointY);
            //establezco el nombre
            figure.setPointPosition(sqPoint);
            
            //establezco la nota
            figure.setWidth(Integer.parseInt(currentElement.getChild("sizex").getValue()));
            
            //establezco la nota
            figure.setLength(Integer.parseInt(currentElement.getChild("sizey").getValue()));
            
            //guardar en el arreglo
            FiguresArray[count++] = figure;
        }//end for
        return FiguresArray;
    }
    
}
