package conexionMongoDb;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import modelo.mundo.Cama;
import modelo.mundo.Paciente;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;


public class HospitalDao {
    private static HospitalDao instance = null;
    private MongoDatabase database;
    private MongoCollection<Document> camasCollection;
    
    public static HospitalDao getInstance() {
        if (instance == null) {
            instance = new HospitalDao();
        }
        
        return instance;
    }
    
    private HospitalDao() {
        MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
        
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            CodecRegistries.fromProviders(PojoCodecProvider
                .builder().automatic(true).build()));
        
        MongoDatabase database = mongoClient
            .getDatabase("hospital")
            .withCodecRegistry(pojoCodecRegistry);
        
        camasCollection = database.getCollection("camas");
    }
    
    public void guardarCamas(Cama[] camas) {				
        if (camasCollection.countDocuments() != 0) return;
        
        for (Cama cama : camas) {
            guardarCama(cama);
        }
    }
    
    public void guardarCama(Cama cama) {					//Create
        Paciente paciente = cama.darPaciente();
        boolean existe = camasCollection.countDocuments(Filters.eq("numero", cama.darNumero())) > 0;

        Document camaDocument;
        Document pacienteDocument = null;

        if (paciente != null) {
            pacienteDocument = new Document()
                .append("cedula", paciente.darCedula())
                .append("apellido", paciente.darApellido())
                .append("nombre", paciente.darNombre())
                .append("fechaIngreso", paciente.darFechaIngreso())
                .append("historial", paciente.darHistorial());
        }
        
        if (existe) {
            camaDocument = camasCollection.find(Filters.eq("numero", cama.darNumero())).first();
        } else {
            camaDocument = new Document()
                .append("_id", new ObjectId())
                .append("numero", cama.darNumero())
                .append("clase", cama.darClase().name())
                .append("ubicacion", cama.darUbicacion().name())
                .append("paciente", pacienteDocument);
        }

        if (existe) {							//Update - Delete
            Bson actualizacion = Updates.combine(
                Updates.set("numero", cama.darNumero()),
                Updates.set("clase", cama.darClase().name()),
                Updates.set("ubicacion", cama.darUbicacion().name()),
                Updates.set("paciente", pacienteDocument)
            );
            camasCollection.updateOne(Filters.eq("numero", cama.darNumero()), actualizacion);
        } else {
            camasCollection.insertOne(camaDocument);
        }
    }
    
    public List<Cama> obtenerCamas() {					//Read
        FindIterable<Document> encontrado = camasCollection.find();
        List<Cama> camas = new ArrayList<Cama>();
        
        for (Document documento : encontrado) {
            int numero = documento.getInteger("numero");
            Cama.Clase clase = Cama.Clase.valueOf(documento.getString("clase"));
            Cama.Ubicacion ubicacion = Cama.Ubicacion.valueOf(documento.getString("ubicacion"));
            Paciente paciente = null;
            
            if (documento.get("paciente") != null) {
                Document pacienteDocument = documento.get("paciente", Document.class);
                
                // String pCedula, String pNombre, String pApellido, String pHistorial, String pFechaIngreso
                paciente = new Paciente(
                    pacienteDocument.getString("cedula"),
                    pacienteDocument.getString("nombre"),
                    pacienteDocument.getString("apellido"),
                    pacienteDocument.getString("historial"),
                    pacienteDocument.getString("fechaIngreso")
                );
            }
            
            Cama cama = new Cama(numero, clase, ubicacion);
            cama.asignarAPaciente(paciente);
            camas.add(cama);
        }
        
        camas.sort((Cama camaA, Cama camaB) -> {
            return Integer.compare(camaA.darNumero(), camaB.darNumero());
        });
        
        return camas;
    }
}
