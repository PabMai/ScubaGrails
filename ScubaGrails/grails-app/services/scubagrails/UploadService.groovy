package scubagrails

import org.codehaus.groovy.grails.web.context.ServletContextHolder;
import org.springframework.web.multipart.MultipartFile;

/**
 * Thanks to cavneb/FileUploader
 * @author MONJAL
 *
 */
class UploadService {

    boolean transactional = true

    def String uploadFile(MultipartFile file,
      String name, String destinationDirectory) {

        def servletContext = ServletContextHolder.servletContext
        def storagePath = servletContext.getRealPath(destinationDirectory)

        // Create storage path directory if it does not exist
        def storagePathDirectory = new File(storagePath)
        if (!storagePathDirectory.exists()) {
            print "Création du dossier ${storagePath}: "
            if (storagePathDirectory.mkdirs()) {
                println "SUCCESS"
            } else {
                println "FAILED"
            }
        }

        // Store file
        if (!file.isEmpty()) {
            file.transferTo(new File("${storagePath}/${name}"))
            println "Fichier sauvegardé: ${storagePath}/${name}"
            return "${storagePath}/${name}"

        } else {
            println "File ${file.inspect()} est vide !"
            return null
        }
    }
}
