package frsf.isi.died.app.dao;

import java.util.Comparator;

import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class DeseoComparator implements Comparator<MaterialCapacitacion>
{
    @Override
    public int compare(MaterialCapacitacion x, MaterialCapacitacion y)
    {
 
    /*	if(x.getRelevancia().ordinal()<y.getRelevancia().ordinal())
    	{
            return -1;
        }
    	if(x.getRelevancia().ordinal()>y.getRelevancia().ordinal())
    	{
            return 1;
        }
    	else{
    		if(x.getCalificacion()<y.getCalificacion())
        	{
                return -1;
            }
        	if(x.getCalificacion()>y.getCalificacion())
        	{
                return 1;
            }
        	else {*/
        		if(x.precio()<y.precio())
            	{
                    return -1;
                }
            	if(x.precio()>y.precio())
            	{
                    return 1;
                }
            	else return 0;
          //  }
        //}
    } }

