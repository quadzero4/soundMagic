package pleaseLast;

import ddf.minim.Minim;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
/**
 * This is a simple implementation of Minim requirements in order to be able to
 * use it outside Processing; in plain Java programs.
 *
 * Usage:
 * Minim instance = MinimImpl.getMinimInstance();
 *
 * Quote from Minim documentation:
 * -----------------------------------------------------------------------------
 * If you are using Minim outside of Processing, then the constructor of Minim
 * requires an Object that can handle two important file system operations
 * so that it doesn¡¯t have to worry about details of the current environment.
 *
 * These two methods are:
 *      String sketchPath( String fileName )
 *      InputStream createInput( String fileName )
 *
 * These are methods that are defined in Processing,
 * which Minim was originally designed to cleanly interface with.
 * The sketchPath method is expected to transform a filename into an absolute path and
 * is used when attempting to create an AudioRecorder (see below).
 * The createInput method is used when loading files and is expected to take a filename,
 * which is not necessarily an absolute path, and return an InputStream that
 * can be used to read the file.
 * For example, in Processing, the createInput method will search in the data folder,
 * the sketch folder, handle URLs, and absolute paths.
 * If you are using Minim outside of Processing,
 * you can handle whatever cases are appropriate for your project.
 * -----------------------------------------------------------------------------
 *
 * Author : Gregory Kotsaftis
 * License: Public Domain.
 */
public final class MinimImpl {
    /**
     * Use this method to obtain a valid Minim instance!
     */
    public static Minim getMinimInstance()
    {
        return new Minim(new MinimImpl());
    }
    /**
     * Override required method.
     */
    public String sketchPath(String fileName)
    {
        return( new File(fileName).getAbsolutePath() );
    }
    /**
     * Override required method.
     */
    public InputStream createInput(String fileName)
        throws FileNotFoundException
    {
        return( new FileInputStream(new File(fileName)) );
    }
}