/******************************************************************************
 * 
 *  @author Haiyue Li 
 *  @username haiyuel @studentID 946453
 *  @tutor Minxian Xu
 *  @Email haiyuel@student.unimelb.edu.au
 *  
 ******************************************************************************/
package ServerSide;

import org.kohsuke.args4j.Option;

public class CmdLineArgs {

    @Option(required = false, name = "-p", aliases = {"--port"}, usage = "Port number")
    private int port = 3000;
    
    public int getPort() {
        return port;
    }
}
